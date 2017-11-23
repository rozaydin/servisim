package com.rhtech.servisim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Passenger extends Person {

    @Enumerated(EnumType.STRING)
    private Attendance attendance;
    @ManyToOne
    @JoinColumn(name = "servis_id")
    private Servis servis;

}
