package com.rhtech.servisim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Passenger extends Person {

    private Attendance attendance;
    @ManyToOne
    @JoinColumn(name = "servis_id", nullable = false)
    private Servis servis;

}
