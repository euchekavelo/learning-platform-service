package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnswerRequestDto {

    @NotNull(message = "Идентификатор вопроса не может быть пустым")
    private Integer questionId;

    @NotNull(message = "Идентификатор преподавателя не может быть пустым")
    private Integer teacherId;

    @NotBlank(message = "Текст ответа не может быть пустым")
    private String text;

    @NotNull(message = "Правильность ответа не может быть пустым")
    private Boolean isCorrect;
}
