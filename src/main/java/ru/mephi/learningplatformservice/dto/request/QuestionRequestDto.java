package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class QuestionRequestDto {

    private Integer userId;
    private Integer quizId;
    private String text;
    private String type;
}
