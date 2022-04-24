package com.demo.fileupload.service.dto.response;

import com.demo.fileupload.service.dto.request.StudentDTO;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StudentResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(StudentResponseDTO.class);

    private Long id;
    private String name;
    private byte[] file;
    private String fileName;

    public StudentResponseDTO toResponseDTO(StudentDTO studentDTO) {
        StudentResponseDTO studentResponseDTO = null;
        try {
            studentResponseDTO = StudentResponseDTO.builder()
                    .id(studentDTO.getId()).name(studentDTO.getName())
                    .file(studentDTO.getFile().getBytes()).fileName(studentDTO.getFileName()).build();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
        }
        return studentResponseDTO;
    }
}
