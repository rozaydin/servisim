package com.rhtech.servisim.repository;

import com.rhtech.servisim.model.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {

    List<Passenger> findAllByServisId(long servisId);

}
