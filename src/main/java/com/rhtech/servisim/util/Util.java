package com.rhtech.servisim.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class Util
{
    public static ResponseEntity createResponseEntity(URI locationUri)
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(locationUri);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
