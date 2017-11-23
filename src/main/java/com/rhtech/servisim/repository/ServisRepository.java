package com.rhtech.servisim.repository;

import com.rhtech.servisim.model.Servis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServisRepository extends CrudRepository<Servis, Long> {

    Servis findByName(String servisName);

    List<Servis> findAllByPlateNumber(String plateNumber);

}
