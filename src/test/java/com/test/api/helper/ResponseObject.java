package com.test.api.helper;

public class ResponseObject {
    private Integer responseCode;
    private String response;

    public ResponseObject(Integer responseCode, String response) {
        this.responseCode = responseCode;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }


}