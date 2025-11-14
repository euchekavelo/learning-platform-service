package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class EnrollmentRequestDto {

    private Integer courseId;
    private Integer userId;
}
