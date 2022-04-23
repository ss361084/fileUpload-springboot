package com.demo.fileupload.web.rest;

import com.demo.fileupload.config.Constants;
import com.demo.fileupload.service.StudentService;
import com.demo.fileupload.service.dto.StudentDTO;
import com.demo.fileupload.service.error.BadRequestException;
import com.demo.fileupload.web.rest.error.NameAlreadyAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentResource {

    private static final Logger log = LoggerFactory.getLogger(StudentResource.class);
    private StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(Constants.API_PREFIX_FOR_MODULE_STUDENT)
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO studentDTO) {
        log.info("Rest Request to Save Student");
        if(studentDTO.getId() != null) {
            throw new BadRequestException("inValid request");
        }
        if(studentService.existByName(studentDTO.getName())) {
            throw new NameAlreadyAvailableException("Name Already available");
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/${id}").buildAndExpand(result.getId()).toUri())
                .body(result);
    }

    @GetMapping(Constants.API_PREFIX_FOR_MODULE_STUDENT)
    public ResponseEntity<List<StudentDTO>> getAll() {
        log.info("Rest Request to Get All Student");
        List<StudentDTO> studentDTOList = studentService.getAll();
        return ResponseEntity.ok(studentDTOList);
    }
}
