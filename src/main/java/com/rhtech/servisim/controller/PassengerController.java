package com.rhtech.servisim.controller;

import com.rhtech.servisim.model.Driver;
import com.rhtech.servisim.model.Passenger;
import com.rhtech.servisim.repository.PassengerRepository;
import com.rhtech.servisim.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController
{
    private PassengerRepository passengerRepository;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository)
    {
        this.passengerRepository = passengerRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createPassenger(@RequestBody Passenger passenger, UriComponentsBuilder uriComponentsBuilder)
    {
        passenger = passengerRepository.save(passenger);
        return Util.createResponseEntity(uriComponentsBuilder
                .path("/{id}")
                .buildAndExpand(passenger.getId())
                .toUri());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPassenger(@PathVariable("id") Long passengerId)
    {
        Passenger passenger = passengerRepository.findOne(passengerId);
        HttpStatus httpStatus = passenger == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(passenger, httpStatus);
    }

    @GetMapping(value = "/servis/{servisId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Passenger> getPassengersOfServis(@PathVariable("servisId") Long servisId) {
        return passengerRepository.findAllByServisId(servisId);
    }

}
