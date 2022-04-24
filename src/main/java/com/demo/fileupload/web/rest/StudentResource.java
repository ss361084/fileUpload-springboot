package com.demo.fileupload.web.rest;

import com.demo.fileupload.config.Constants;
import com.demo.fileupload.service.StudentService;
import com.demo.fileupload.service.dto.request.StudentDTO;
import com.demo.fileupload.service.dto.response.StudentResponseDTO;
import com.demo.fileupload.service.error.BadRequestException;
import com.demo.fileupload.web.rest.error.FileNotFoundException;
import com.demo.fileupload.web.rest.error.InValidFileSizeException;
import com.demo.fileupload.web.rest.error.NameAlreadyAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class StudentResource {

    private static final Logger log = LoggerFactory.getLogger(StudentResource.class);
    private StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(Constants.API_PREFIX_FOR_MODULE_STUDENT)
    public ResponseEntity<StudentResponseDTO> save(@Valid @ModelAttribute StudentDTO studentDTO) {
        log.info("Rest Request to Save Student");
        if(studentDTO.getId() != null) {
            throw new BadRequestException("inValid request");
        }
        if(studentService.existByName(studentDTO.getName())) {
            throw new NameAlreadyAvailableException("Name Already available");
        }
        if(!validateFile(studentDTO.getFile())) {
            throw new FileNotFoundException("File Not Found");
        }
        StudentDTO result = studentService.save(studentDTO);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO().toResponseDTO(result);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/${id}").buildAndExpand(studentResponseDTO.getId()).toUri())
                .body(studentResponseDTO);
    }

    @GetMapping(Constants.API_PREFIX_FOR_MODULE_STUDENT)
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        log.info("Rest Request to Get All Student");
        List<StudentResponseDTO> studentDTOList = studentService.getAll().stream().map(new StudentResponseDTO() :: toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOList);
    }

    private boolean validateFile(MultipartFile file) {
        long ALLOWED_MB_FILE = 5;
        File tempFile = null;
        long fileSizeInMB = 0;
        try {
            if(file == null || file.isEmpty()) {
                return false;
            }
            Path filePath = Paths.get(System.getProperty("java.io.tmpdir"),file.getOriginalFilename());
            file.transferTo(filePath);
            tempFile = filePath.toFile();
            fileSizeInMB = (tempFile.length() / 1024) / 1024;
        } catch (Exception ex) {
                log.error(ex.getMessage(), ex.getCause());
        } finally {
            if (tempFile != null) {
                tempFile.deleteOnExit();
            }
        }
        if(fileSizeInMB > ALLOWED_MB_FILE) {
            throw new InValidFileSizeException("max file size allowed 5 MB");
        }
        return true;
    }
}
