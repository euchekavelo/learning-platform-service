package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class EvaluateRequestDto {

    private Integer userId;
    private Integer score;
    private String feedback;
}
