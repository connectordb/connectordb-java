package com.connectordb.client;


public class RequestFailedException extends Exception {
    public ErrorResponse response;
    public RequestFailedException(ErrorResponse response) {
        super(response.msg);
        this.response = response;
    }
}
