package com.rhtech.servisim.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Driver extends Person {

    @OneToMany(targetEntity = com.rhtech.servisim.model.Servis.class, cascade = CascadeType.ALL, mappedBy = "driver")
    private Set<Servis> servisSet;

}