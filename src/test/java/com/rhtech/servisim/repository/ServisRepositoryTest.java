package com.rhtech.servisim.repository;

import com.rhtech.servisim.model.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServisRepositoryTest {

    @Autowired
    private ServisRepository servisRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Test
    public void verifyRecordCanBeSaved() {

        Driver driver = new Driver();
        driver.setName("Hasan Amca");
        driver.setPhoneNumber("05554519960");

        driverRepository.save(driver);
        assertThat(driverRepository.findOne(driver.getId())).extracting("id").containsExactly(driver.getId());

    }

}
