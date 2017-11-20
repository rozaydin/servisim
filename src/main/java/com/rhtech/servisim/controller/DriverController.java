package com.rhtech.servisim.controller;

import com.rhtech.servisim.model.Driver;
import com.rhtech.servisim.repository.DriverRepository;
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
public class DriverController {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createDriver(@RequestBody Driver driver, UriComponentsBuilder uriComponentsBuilder) {

        driver = driverRepository.save(driver);

        UriComponents uriComponents =
                uriComponentsBuilder.path("/{id}").buildAndExpand(driver.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDriver(@PathVariable("id") Long driverId) {

        Driver driver = driverRepository.findOne(driverId);
        HttpStatus httpStatus = driver == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(driver, httpStatus);
    }

}
