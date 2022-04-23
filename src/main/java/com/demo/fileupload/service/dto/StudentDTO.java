package com.demo.fileupload.service.dto;

import com.demo.fileupload.service.error.ErrorConstant;
import lombok.*;

import javax.validation.constraints.NotEmpty;
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
}
