package com.lits.rubinskyy.restutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

import java.io.IOException;

public class ResponseEntity<T> {

    private Response response;
    private ObjectMapper mapper;
    private Class<T> clazz;

    public ResponseEntity(Response response, Class<T> clazz, ObjectMapper mapper) {
        this.response = response;
        this.mapper = mapper;
        this.clazz = clazz;
    }

    public T readEntity() {
        try {
            return mapper.readValue(response.body().string(), clazz);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
