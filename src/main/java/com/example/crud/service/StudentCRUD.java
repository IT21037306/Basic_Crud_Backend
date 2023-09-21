package com.example.crud.service;

import com.example.crud.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface StudentCRUD<S , ID> {
    S save(S s);

    Page<S> findAll(Pageable pageable);

    S findById(ID id);

    S update(ID id, S s);

    ResponseEntity<StudentDto> delete(ID id);
}
