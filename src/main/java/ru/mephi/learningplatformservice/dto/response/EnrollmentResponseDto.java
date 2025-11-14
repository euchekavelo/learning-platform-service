package ru.mephi.learningplatformservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EnrollmentResponseDto {

    private Integer id;
    private Date enrollDate;
    private String status;
}
