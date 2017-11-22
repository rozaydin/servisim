package com.rhtech.servisim.model;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String phoneNumber;

}
