package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class QuizSubmissionResponseDto {

    private Integer id;
    private Integer score;
    private Integer quizId;
}
