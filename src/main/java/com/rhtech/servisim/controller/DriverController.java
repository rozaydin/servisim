package com.rhtech.servisim.controller;

import com.rhtech.servisim.model.Driver;
import com.rhtech.servisim.repository.DriverRepository;
import com.rhtech.servisim.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/driver")
public class DriverController
{

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createDriver(@RequestBody Driver driver, UriComponentsBuilder uriComponentsBuilder)
    {

        driver = driverRepository.save(driver);
        return Util.createResponseEntity(uriComponentsBuilder
                .path("/{id}")
                .buildAndExpand(driver.getId())
                .toUri());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDriver(@PathVariable("id") Long driverId)
    {

        Driver driver = driverRepository.findOne(driverId);
        HttpStatus httpStatus = driver == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(driver, httpStatus);
    }

}
