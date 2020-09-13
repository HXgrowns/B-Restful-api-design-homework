package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
                .name("huxiao")
                .note("")
                .gender(Gender.FEMALE)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String studentString = objectMapper.writeValueAsString(student);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentString);
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("huxiao")));
    }

    @Test
    public void should_delete_student_by_id() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/students/1");
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void should_find_students_by_Gender() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String genderString = objectMapper.writeValueAsString(Gender.MALE);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(genderString);
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("lisi")));
    }

    @Test
    public void should_find_student_by_id() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/students/1");
        result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("zhangsan")));

    }
}