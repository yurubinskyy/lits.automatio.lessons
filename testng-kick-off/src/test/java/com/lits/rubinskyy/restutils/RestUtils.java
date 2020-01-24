package com.lits.rubinskyy.restutils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;

public class RestUtils {

    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    private ObjectMapper mapper = new ObjectMapper();

    public RestUtils() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> ResponseEntity<T> GET(String url, Class<T> clazz) {
        return GET(url, Headers.of(), clazz);
    }

    public <T> ResponseEntity<T>  GET (String url, Headers headers, Class<T> clazz) {

        final Request request = new Request.Builder()
                .headers(headers)
                .build();

        try
        {
            final Response execute = httpClient.newCall(request).execute();
            return new ResponseEntity<>(execute, clazz, mapper);
        }
        catch (IOException e)
        {
           throw  new RuntimeException(e);
        }
    }
}
