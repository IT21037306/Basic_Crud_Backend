package com.example.crud.repo;

import com.example.crud.model.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRedisDao {
    public static final String Hash_Key = "Student";

    private RedisTemplate template;

    public Student save(Student student){
        template.opsForHash().put(Hash_Key,student.getStudentId(),student);
        return student;
    }

    public List<Student> getAll(){
        return template.opsForHash().values(Hash_Key);
    }

    public Student findStudentById(Integer id){
        return (Student) template.opsForHash().get(Hash_Key,id);
    }

    public String deleteStudent(Integer id){
        template.opsForHash().delete(Hash_Key,id);
        return "Student Deleted Successfully";
    }

}
