package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.BussinessException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "zhangsan", Gender.FEMALE, ""),
            new Student(2, "lisi", Gender.MALE, ""),
            new Student(3, "wangwu", Gender.FEMALE, "")));

    public Student addStudent(Student student) {
        student.setId(students.size() + 1);
        students.add(student);
        return student;
    }

    public void delete(Integer id) {
        Student deleteStudent = students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BussinessException(ExceptionEnum.NOT_FIND_STUDENT));
        students.remove(deleteStudent);
    }
}
