package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
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
    public void should_add_student() throws Exception {
        Student student = Student.builder()
                .name("zhangsan")
                .note("")
                .gender(Gender.FEMALE)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(student);

        MockHttpServletRequestBuilder url = MockMvcRequestBuilders.post("http://localhost:8080/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(string);
        result = mockMvc.perform(url);
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("zhangsan")));
    }
}