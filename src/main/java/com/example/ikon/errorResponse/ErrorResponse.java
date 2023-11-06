package com.example.ikon.errorResponse;

public class ErrorResponse<T> {

    private String message;
    private T data;


    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }

    public ErrorResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }



}
