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
import okhttp3.HttpUrl;


public class ConnectorDB {
	public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");
	public static final String CONNECTORDB_URL = "https://connectordb.com";

	private OkHttpClient client;
	private HttpUrl url;

	public ConnectorDB(String username,String password,String url) {
		// https://corner.squareup.com/2015/05/okhttp-2-4.html
		this.url = HttpUrl.parse(url);

		// https://stackoverflow.com/questions/22490057/android-okhttp-with-basic-authentication
		client = new OkHttpClient.Builder()
			.addInterceptor(new BasicAuthInterceptor(username,password))
	    .connectTimeout(10, TimeUnit.SECONDS)
	    .writeTimeout(10, TimeUnit.SECONDS)
	    .readTimeout(30, TimeUnit.SECONDS)
			.build();
	}


	public String ping() throws Exception {

		Request request = new Request.Builder()
			.url(url.newBuilder().addPathSegment("api/v1").addQueryParameter("q","this").build())
			.build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}
