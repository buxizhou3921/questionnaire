package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponseEntity {
    private String code;
    private Object data;
    private String message;

    public static HttpResponseEntity buildSuccess(Object data, String message){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity("666", data, message);
        return httpResponseEntity;
    }
    public static HttpResponseEntity buildError(Object data, String message){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity("0", data, message);
        return httpResponseEntity;
    }

    public static HttpResponseEntity buildError(String massage) {
        return new HttpResponseEntity("0",null,massage);
    }
}
