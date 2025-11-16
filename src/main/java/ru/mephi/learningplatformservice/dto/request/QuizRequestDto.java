package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class QuizRequestDto {

    private Integer userId;
    private Integer moduleId;
    private String title;
    private Integer timeLimit;
}
