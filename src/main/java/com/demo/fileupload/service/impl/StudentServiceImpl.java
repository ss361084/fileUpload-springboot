package com.demo.fileupload.service.impl;

import com.demo.fileupload.domain.Student;
import com.demo.fileupload.repository.StudentRepository;
import com.demo.fileupload.service.StudentService;
import com.demo.fileupload.service.dto.StudentDTO;
import com.demo.fileupload.service.error.BadRequestException;
import com.demo.fileupload.service.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.info("Request to Save Student DTO");
        if(studentDTO.getId() != null) {
            throw new BadRequestException("InValid Request");
        }
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDTO(student);
    }

    @Override
    public List<StudentDTO> getAll() {
        log.info("Request to Get All Student DTO");
        return studentRepository.findAll().stream()
                .map(studentMapper :: toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getByID(Long id) {
        log.info("Request to Get Student DTO By ID");
        if(id == null) {
            throw new BadRequestException("InValid Request");
        }
        return studentRepository.findById(id)
                .map(studentMapper :: toDTO);
    }

    @Override
    public boolean existByName(String name) {
        log.info("Request to check Student Name is Exist or Not");
        return studentRepository.existsByNameIgnoreCase(name);
    }

}
