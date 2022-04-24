package com.demo.fileupload.service.dto.request;

import com.demo.fileupload.service.error.ErrorConstant;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = ErrorConstant.INVALID_NAME)
    private String name;

    @NotNull(message = ErrorConstant.FILE_NOT_FOUND)
    private MultipartFile file;

    @NotNull(message = ErrorConstant.FILE_NAME_NOT_FOUND)
    private String fileName;
}
