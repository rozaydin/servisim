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
@TestPropertySource(locations = "classpath:test.properties")
public class PassengerRepositoryITest
{

    @Autowired
    private ServisRepository servisRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Before
    public void init()
    {
        List<Passenger> passengerList = new ArrayList<>();

        Driver driver = new Driver();
        driver.setName("Test Driver");
        driver.setPhoneNumber("05554519909");

        driverRepository.save(driver);

        Servis servis = new Servis();
        servis.setImageUrl("url://test-url");
        servis.setPlateNumber("34TM3985");
        servis.setMorningHour(8);
        servis.setAfternoonHour(17);
        // set servis driver
        servis.setDriver(driver);

        servisRepository.save(servis);

        IntStream.range(0, 10).forEach((i) -> {
            Passenger passenger = new Passenger();
            passenger.setAttendance(Attendance.ALL_DAY_ABSENT);
            passenger.setName("Test-Passenger" + i);
            passenger.setPhoneNumber(String.valueOf(i));
            passenger.setServis(servis);
            // store passenger
            passengerList.add(passenger);
        });

        passengerRepository.save(passengerList);
    }

    @After
    public void cleanUp()
    {

        // passengerRepository.deleteAll();
        // servisRepository.deleteAll();
        // driverRepository.deleteAll();
        Iterable<Servis> servisList = servisRepository.findAll();
        servisList.forEach((servis) -> {
            System.out.println(servis.getName());
        });

    }

   /* @Test
    public void testStream()
    {

        int SIZE = 10;

        for (int i = 0; i < SIZE; i++)
        {

            System.out.print("(" + i / SIZE + "," + i % SIZE + ")");

            if (i % SIZE == 0)
            {
                System.out.println("");
            }
        }
    }*/

    @Test
    public void verifyPassengersCanBeRetrieved()
    {

        System.out.println("Hello!");

    }

}
