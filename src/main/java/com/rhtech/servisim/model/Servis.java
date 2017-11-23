package com.rhtech.servisim.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Servis {

    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;
    @Column(unique=true)
    private String name;
    private String plateNumber;
    private String imageUrl;
    private long morningHour;
    private long afternoonHour;

}
