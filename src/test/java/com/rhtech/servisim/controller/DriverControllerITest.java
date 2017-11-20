package com.rhtech.servisim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhtech.servisim.model.Driver;
import com.rhtech.servisim.repository.DriverRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class DriverControllerITest
{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DriverRepository driverRepository;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
    }

    @Test
    public void verifyDriverCanCreatedAndRetrieved() throws Exception
    {

        Driver driver = new Driver();
        driver.setName("Test Driver");
        driver.setPhoneNumber("05554519909");

        ObjectMapper mapper = new ObjectMapper();
        String jsonDriver = mapper.writeValueAsString(driver);

        MockHttpServletResponse response = mockMvc.perform(post("/driver").content(jsonDriver).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.containsHeader("location")).isTrue();
        String locationHeader = response.getHeader("location");
        String driverId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);

        Driver retrievedDriver = driverRepository.findOne(Long.valueOf(driverId));
        assertThat(retrievedDriver).isNotNull();
        assertThat(retrievedDriver).isEqualToComparingOnlyGivenFields(driver, "name", "phoneNumber");


                /*.andDo((result) -> {
            String locationHeader = result.getResponse().getHeader("location");
            driverId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
        });*/
        // .andExpect(header().string("location", "test"));

        // mockMvc.perform(get("/driver/14")).andExpect(status().isNotFound());

    }
}

