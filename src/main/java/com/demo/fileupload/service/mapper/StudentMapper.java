package com.demo.fileupload.service.mapper;

import com.demo.fileupload.domain.Student;
import com.demo.fileupload.service.dto.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EnityMapper<StudentDTO, Student> {

    public StudentDTO toDTO(Student student);
    public Student toEntity(StudentDTO studentDTO);
}
