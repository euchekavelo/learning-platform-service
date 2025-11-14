package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class SubmissionRequestDto {

    private Integer userId;
    private Integer assignmentId;
    private String content;
}
