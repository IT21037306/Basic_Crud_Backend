package com.example.crud.controller;

import com.example.crud.customException.serverException;
import com.example.crud.model.Student;
import com.example.crud.repo.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class StudentController {


    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("api/students")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
            return new ResponseEntity<Student>(studentRepo.save(student),HttpStatus.OK);
    }

    @GetMapping("api/students")
    public ResponseEntity<List<Student>> getAllStudents() throws serverException {
        List<Student> students = studentRepo.findAll();

        if (students.size() > 0)
            return new ResponseEntity<>(students,HttpStatus.OK);
        else{
            throw new serverException("No Users Found !!!");
        }
    }

    //path variable should = api URL
    @GetMapping("api/students/{id}")
    public ResponseEntity<Student> getAStudent(@PathVariable Integer id) throws serverException {
        Optional<Student> student = studentRepo.findById(id);

        if(student.isPresent())
            //we cant use student obj bcz it Optional type
            return new ResponseEntity<Student>(student.get(),HttpStatus.FOUND);
        else
            throw new serverException("No User Found !!! with Id " + id);
    }


    //path variable should = api URL
    //Edit all values in one record
    @PutMapping("api/students/{id}")
    public ResponseEntity<Student> updateStudentDetails(@PathVariable Integer id,@RequestBody @Valid Student student) throws serverException {
        Optional<Student> student1 = studentRepo.findById(id);

        if (student1.isPresent()) {
            student1.get().setName(student.getName());
            student1.get().setAge(student.getAge());
            student1.get().setAddress(student.getAddress());

            return new ResponseEntity<Student>(studentRepo.save(student1.get()), HttpStatus.CREATED);
        }else{
            throw new serverException("No User Found !!! with Id " + id);

        }
    }

    @PatchMapping("api/students/{id}")
    public ResponseEntity<Student> updateStudentDetail(@PathVariable Integer id,@RequestBody Student student) throws serverException {
        Optional<Student> student1 = studentRepo.findById(id);

        if (student1.isPresent()) {
            if(!Objects.equals(student.getName(), student1.get().getName())){
                student1.get().setName(student.getName());
            } else if (!Objects.equals(student.getAge(), student1.get().getAge())) {
                student1.get().setAge(student.getAge());
            } else if (!Objects.equals(student.getAddress(), student1.get().getAddress())) {
                student1.get().setAddress(student.getAddress());
            }

            return new ResponseEntity<Student>(studentRepo.save(student1.get()), HttpStatus.CREATED);
        }else{
            throw new serverException("No User Found !!! with Id " + id);
        }
    }

    @DeleteMapping ("api/students/{id}")
    public ResponseEntity<Student> deleteAStudent(@PathVariable Integer id) throws serverException {
        Optional<Student> student1 = studentRepo.findById(id);
        
        if (student1.isPresent()){
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new serverException("No User Found !!! with Id " + id);
        }
    }


}
