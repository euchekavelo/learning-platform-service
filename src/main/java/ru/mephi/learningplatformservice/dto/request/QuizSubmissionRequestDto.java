package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class QuizSubmissionRequestDto {

    private Integer studentId;
    private Integer quizId;
    private Map<Integer, Integer> answers;
}
