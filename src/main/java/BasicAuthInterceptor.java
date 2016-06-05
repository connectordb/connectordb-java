package com.connectordb.client;

// This class is heavily based upon
// swagger-codegen-master/samples/client/petstore/java/retrofit2/src/main/java/io/swagger/client/auth/HttpBasicAuth.java

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class BasicAuthInterceptor implements Interceptor {

    private String username;
    private String password;

    BasicAuthInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") == null) {
            String credentials = Credentials.basic(username, password);
            request = request.newBuilder()
                      .addHeader("Authorization", credentials)
                      .build();
        }
        return chain.proceed(request);
    }
}
