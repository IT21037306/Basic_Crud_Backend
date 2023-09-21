package com.example.crud.controller;

import com.example.crud.customException.serverException;
import com.example.crud.dto.StudentDto;
import com.example.crud.model.Student;
import com.example.crud.repo.StudentRepo;
import com.example.crud.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
@CrossOrigin(origins = "http://localhost:5173/")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentDto addStudent(@RequestBody @Valid StudentDto student) {
        return studentService.save(student);
    }

    @GetMapping
    public Page<StudentDto> getAllStudents(@RequestParam int page, int limit) {
        return studentService.findAll(PageRequest.of(page, limit));
    }

    @PatchMapping("/{id}")
    public StudentDto updateStudent(@PathVariable Integer id, @RequestBody StudentDto student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Integer id) {
        return studentService.delete(id);
    }

}
