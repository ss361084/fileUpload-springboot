package com.demo.fileupload.service.mapper;

import com.demo.fileupload.domain.Student;
import com.demo.fileupload.service.dto.request.StudentDTO;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;

@Mapper(componentModel = "spring", uses = {})
public interface StudentMapper extends EnityMapper<StudentDTO, Student> {

    static final Logger log = LoggerFactory.getLogger(StudentMapper.class);

    public default StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setFile(byteArrayToMockMultipartFile(student.getFile(), student.getFileName()));
        studentDTO.setFileName(student.getFileName());
        return studentDTO;
    }
    public default Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        try {
            student.setId(studentDTO.getId());
            student.setName(studentDTO.getName());
            student.setFile(studentDTO.getFile().getBytes());
            student.setFileName(studentDTO.getFileName());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
        }
        return student;
    }

    private MockMultipartFile byteArrayToMockMultipartFile(byte[] fileContent, String fileName) {
        return new MockMultipartFile(fileName, fileContent);
    }
}
