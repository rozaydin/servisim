package com.rhtech.servisim.repository;

import com.rhtech.servisim.model.Attendance;
import com.rhtech.servisim.model.Driver;
import com.rhtech.servisim.model.Passenger;
import com.rhtech.servisim.model.Servis;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:integration-test.properties")
public class PassengerRepositoryITest
{
    @Autowired
    private ServisRepository servisRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    // servis plate number
    private final String plateNumber = "34TM3985";
    private final String servisName = "SiemensNarcity";

    @Before
    public void init()
    {

        Driver driver = new Driver();
        driver.setName("Test Driver");
        driver.setPhoneNumber("05554519909");

        driverRepository.save(driver);

        Servis servis = new Servis();
        servis.setName(servisName);
        servis.setImageUrl("url://test-url");
        servis.setPlateNumber(plateNumber);
        servis.setMorningHour(8);
        servis.setAfternoonHour(17);
        // set servis driver
        servis.setDriver(driver);

        servisRepository.save(servis);
    }

    @After
    public void cleanUp()
    {
        passengerRepository.deleteAll();
        servisRepository.deleteAll();
        driverRepository.deleteAll();
    }

    @Test
    public void verifyPassengersInAServisCanBeRetrieved()
    {
        Servis servis = servisRepository.findByName(servisName);
        List<Passenger> passengerList = new ArrayList<>();

        IntStream.range(0, 10).forEach((i) -> {
            Passenger passenger = createPassenger("Test-Passenger" + i, String.valueOf(i), Attendance.ALL_DAY_ABSENT);
            passenger.setServis(servis);
            // store passenger
            passengerList.add(passenger);
        });

        passengerRepository.save(passengerList);

        List<Passenger> passengers = passengerRepository.findAllByServisId(servis.getId());
        assertThat(passengers).containsAll(passengerList);
    }

    @Test
    public void verifyPassengerWithoutServisCanBeCreated()
    {

        Passenger passenger = createPassenger("05554519909", "Rıdvan Özaydın", Attendance.ALL_DAY_ABSENT);
        passengerRepository.save(passenger);
        Passenger retrievedPassenger = passengerRepository.findOne(passenger.getId());
        assertThat(retrievedPassenger).isEqualToComparingFieldByFieldRecursively(passenger);
    }

    @Test
    public void verifyPassengerCanSetAttendance()
    {

        Passenger passenger = createPassenger("05554519909", "Rıdvan Özaydın", Attendance.ALL_DAY_ABSENT);
        passengerRepository.save(passenger);
        // coming
        passenger.setAttendance(Attendance.ATTENDING);
        passengerRepository.save(passenger);
        Passenger retrievedPassenger = passengerRepository.findOne(passenger.getId());
        assertThat(retrievedPassenger).extracting("name", "phoneNumber", "attendance")
                .containsExactly("Rıdvan Özaydın", "05554519909", Attendance.ATTENDING);
    }

    private Passenger createPassenger(String phoneNumber, String name, Attendance attendance)
    {
        Passenger passenger = new Passenger();
        passenger.setPhoneNumber(phoneNumber);
        passenger.setName(name);
        passenger.setAttendance(attendance);
        return passenger;
    }

}
