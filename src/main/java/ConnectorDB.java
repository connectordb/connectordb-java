package com.connectordb.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// https://square.github.io/okhttp/3.x/okhttp/
import okhttp3.MediaType;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.HttpUrl;

import com.google.gson.Gson;



public class ConnectorDB {
    protected static final MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    public static final String CONNECTORDB_URL = "https://connectordb.com";

    protected final Gson gson = new Gson();

    protected OkHttpClient client;
    protected HttpUrl url;

    public ConnectorDB(String username,String password,String url) {
        // https://corner.squareup.com/2015/05/okhttp-2-4.html
        this.url = HttpUrl.parse(url).newBuilder().addPathSegment("api/v1").build();

        // https://stackoverflow.com/questions/22490057/android-okhttp-with-basic-authentication
        client = new OkHttpClient.Builder()
                 .addInterceptor(new BasicAuthInterceptor(username,password))
                 .connectTimeout(10, TimeUnit.SECONDS)
                 .writeTimeout(10, TimeUnit.SECONDS)
                 .build();
    }



    private HttpUrl.Builder crudUrl() {
        return url.newBuilder().addPathSegment("crud");
    }

    private void throwFailure(Response response) throws RequestFailedException {
        if (!response.isSuccessful()) {
            throw new RequestFailedException(gson.fromJson(response.body().charStream(),ErrorResponse.class));
        }
    }





    public String ping() throws Exception {

        Request request = new Request.Builder()
                          .url(url.newBuilder().addQueryParameter("q","this").build())
                          .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private Response simpleGet(String path) throws Exception, RequestFailedException {
        Request request = new Request.Builder()
                          .url(crudUrl().addPathSegment(path).build())
                          .build();

        Response response = client.newCall(request).execute();
        throwFailure(response);
        return response;
    }
    private Response simplePost(String path,String data) throws Exception, RequestFailedException {
        Request request = new Request.Builder()
                          .url(crudUrl().addPathSegment(path).build())
                          .post(RequestBody.create(JSON,data))
                          .build();

        Response response = client.newCall(request).execute();
        throwFailure(response);
        return response;
    }
    private Response simplePut(String path,String data) throws Exception, RequestFailedException {
        Request request = new Request.Builder()
                          .url(crudUrl().addPathSegment(path).build())
                          .put(RequestBody.create(JSON,data))
                          .build();

        Response response = client.newCall(request).execute();
        throwFailure(response);
        return response;
    }
    private Response simpleDelete(String path) throws Exception, RequestFailedException {
        Request request = new Request.Builder()
                          .url(crudUrl().addPathSegment(path).build())
                          .delete()
                          .build();

        Response response = client.newCall(request).execute();
        throwFailure(response);
        return response;
    }

    public User getUser(String name) throws Exception,RequestFailedException {
        Response response = simpleGet(name);

        return gson.fromJson(response.body().charStream(),User.class);
    }
    public Device getDevice(String path) throws Exception,RequestFailedException {
        Response response = simpleGet(path);

        return gson.fromJson(response.body().charStream(),Device.class);
    }
    public Stream getStream(String path) throws Exception,RequestFailedException {
        Response response = simpleGet(path);

        return gson.fromJson(response.body().charStream(),Stream.class);
    }

    public User createUser(String name,User usr)  throws Exception,RequestFailedException {
        Response response = simplePost(name,gson.toJson(usr.updates));
        return gson.fromJson(response.body().charStream(),User.class);
    }
    public Device createDevice(String path, Device device)  throws Exception,RequestFailedException {
        Response response = simplePost(path,gson.toJson(device.updates));
        return gson.fromJson(response.body().charStream(),Device.class);
    }
    public Stream createStream(String path, Stream stream)  throws Exception,RequestFailedException {
        Response response = simplePost(path,gson.toJson(stream.updates));
        return gson.fromJson(response.body().charStream(),Stream.class);
    }

    public User updateUser(String name,User usr)  throws Exception,RequestFailedException {
        Response response = simplePut(name,gson.toJson(usr.updates));
        return gson.fromJson(response.body().charStream(),User.class);
    }
    public Device updateDevice(String path, Device device)  throws Exception,RequestFailedException {
        Response response = simplePut(path,gson.toJson(device.updates));
        return gson.fromJson(response.body().charStream(),Device.class);
    }
    public Stream updateStream(String path, Stream stream)  throws Exception,RequestFailedException {
        Response response = simplePut(path,gson.toJson(stream.updates));
        return gson.fromJson(response.body().charStream(),Stream.class);
    }

    public void deleteUser(String name)  throws Exception,RequestFailedException {
        simpleDelete(name);
    }
    public void deleteDevice(String name)  throws Exception,RequestFailedException {
        simpleDelete(name);
    }
    public void deleteStream(String name)  throws Exception,RequestFailedException {
        simpleDelete(name);
    }

    // insertJson is a temporary measure to allow inserting from the android app.
    // The java library is DEFINITELY not finished yet....
    public void insertJson(String name,String datapointarrayjsonstring) throws Exception,RequestFailedException {
        simplePut(name+"/data",datapointarrayjsonstring);
    }
}
