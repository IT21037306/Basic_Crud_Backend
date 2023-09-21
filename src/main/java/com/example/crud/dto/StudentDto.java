package com.example.crud.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class StudentDto {

    private int studentId;
    @NotNull(message = "Name should not be empty")
    private String name;
    @Min(18)
    @Max(50)
    @NotNull(message = "Age should not be empty")
    private int age;
    @NotNull(message = "Address should not be empty")
    private  String address;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
