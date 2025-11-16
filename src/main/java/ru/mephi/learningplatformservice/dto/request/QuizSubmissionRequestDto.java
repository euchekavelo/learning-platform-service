package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class QuizSubmissionRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer studentId;

    @NotNull(message = "Идентификатор теста не может быть пустым")
    private Integer quizId;

    @NotNull(message = "Ответы не могут быть пустыми")
    private Map<Integer, Integer> answers;
}
