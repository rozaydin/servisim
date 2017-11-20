package com.rhtech.servisim.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Person {

    @Id
    private long id;

    private String name;
    private String phoneNumber;

}
