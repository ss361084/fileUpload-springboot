package com.demo.fileupload.service;

import com.demo.fileupload.service.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);

    List<StudentDTO> getAll();

    Optional<StudentDTO> getByID(Long id);

    boolean existByName(String name);
}
