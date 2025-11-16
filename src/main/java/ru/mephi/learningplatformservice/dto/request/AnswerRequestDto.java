package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class AnswerRequestDto {

    private Integer questionId;
    private Integer teacherId;
    private String text;
    private Boolean isCorrect;
}
