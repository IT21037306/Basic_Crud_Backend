package com.example.crud.service.impl;

import com.example.crud.dto.StudentDto;
import com.example.crud.model.Student;
import com.example.crud.repo.StudentRepo;
import com.example.crud.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepo studentRepo;

    public StudentServiceImpl(ModelMapper modelMapper, StudentRepo studentRepo) {
        this.modelMapper = modelMapper;
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        return modelMapper.map(studentRepo.save(modelMapper.map(studentDto, Student.class)),StudentDto.class);
    }

    @Override
    public Page<StudentDto> findAll(Pageable pageable) {
        return studentRepo.findAll(pageable).map(student -> modelMapper.map(student,StudentDto.class));
    }

    @Override
    public StudentDto findById(Integer integer) {
        return null;
    }

    @Override
    public StudentDto update(Integer integer, StudentDto student) {
        Student findStudent = studentRepo.findById(integer).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not found"));

        if(!Objects.equals(findStudent.getName(), student.getName())){
                findStudent.setName(student.getName());
            } else if (!Objects.equals(findStudent.getAge(), student.getAge())) {
                findStudent.setAge(student.getAge());
            } else if (!Objects.equals(findStudent.getAddress(), student.getAddress())) {
                findStudent.setAddress(student.getAddress());
            }

        return modelMapper.map(studentRepo.save(findStudent),StudentDto.class);
    }

    @Override
    public ResponseEntity<StudentDto> delete(Integer integer) {
        Optional<Student> findStudent = studentRepo.findById(integer);

        if(findStudent.isPresent()){
            studentRepo.deleteById(findStudent.get().getStudentId());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
