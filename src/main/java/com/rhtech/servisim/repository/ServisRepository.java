package com.rhtech.servisim.repository;

import com.rhtech.servisim.model.Servis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServisRepository extends CrudRepository<Servis, Long> {

}
