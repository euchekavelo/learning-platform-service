package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class QuizResponseDto {

    private Integer id;
    private String title;
    private Integer timeLimit;
}
