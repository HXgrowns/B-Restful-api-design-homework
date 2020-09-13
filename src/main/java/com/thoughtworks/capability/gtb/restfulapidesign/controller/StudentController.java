package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addStudent(student));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        studentService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllByGender(@RequestParam(required = false) Gender gender) {
        return ResponseEntity.ok().body(studentService.findAllByGender(gender));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student ) {
        return ResponseEntity.ok().body(studentService.update(student));
    }
}
