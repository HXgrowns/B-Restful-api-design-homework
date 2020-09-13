package com.thoughtworks.capability.gtb.restfulapidesign.controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ResultActions result;

    @AfterEach
    void tearDown() throws UnsupportedEncodingException {
        if (result != null) {
            String contentAsString = result.andReturn()
                    .getResponse()
                    .getContentAsString(StandardCharsets.UTF_8);
            System.out.println(contentAsString);
        }
    }

    @Test
    public void getTeams() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/api/vi/teams?teamCount=6");
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_update_team_name() throws Exception {
        result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/vi/teams?teamCount=6"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch("http://localhost:8080/api/vi/teams/1?teamName=haha");
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("haha")));
    }
}