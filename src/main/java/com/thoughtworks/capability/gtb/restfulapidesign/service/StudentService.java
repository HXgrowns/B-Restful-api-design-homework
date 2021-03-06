package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.BussinessException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    public static final List<Student> STUDENTS = new ArrayList<>(Arrays.asList(
            new Student(1, "zhangsan", Gender.FEMALE, ""),
            new Student(2, "lisi", Gender.MALE, ""),
            new Student(3, "wangwu", Gender.FEMALE, "")));

    public Student addStudent(Student student) {
        student.setId(STUDENTS.size() + 1);
        STUDENTS.add(student);
        return student;
    }

    public void delete(int id) {
        Student deleteStudent = STUDENTS.stream()
                .filter(student -> id == student.getId())
                .findFirst()
                .orElseThrow(() -> new BussinessException(ExceptionEnum.NOT_FIND_STUDENT));
        STUDENTS.remove(deleteStudent);
    }

    public List<Student> findAllByGender(Gender gender) {
        if (gender == null) {
            return STUDENTS;
        }

        return STUDENTS.stream().filter(student -> gender == student.getGender())
                .collect(Collectors.toList());
    }

    public Student findById(int id) {
        return STUDENTS.stream().filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Student update(Student newStudent) {
        if (newStudent == null || newStudent.getId() == null) {
            throw new BussinessException(ExceptionEnum.PARAM_IS_INVALID);
        }
        for (int i = 0; i < STUDENTS.size(); i++) {
            if(newStudent.getId().equals(STUDENTS.get(i).getId())) {
                STUDENTS.set(i,newStudent);
                return newStudent;
            }
        }
        throw new BussinessException(ExceptionEnum.NOT_FIND_STUDENT) ;
    }
}
