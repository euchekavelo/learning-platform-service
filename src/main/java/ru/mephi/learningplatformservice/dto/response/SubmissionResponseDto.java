package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class SubmissionResponseDto {

    private Integer id;
    private String content;
    private Integer score;
    private String feedback;
    private Integer studentId;
}
