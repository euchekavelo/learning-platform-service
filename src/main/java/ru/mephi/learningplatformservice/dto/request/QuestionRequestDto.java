package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class QuestionRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор теста не может быть пустым")
    private Integer quizId;

    @NotBlank(message = "Текст вопроса не может быть пустым")
    private String text;

    @Pattern(regexp = "SINGLE_CHOICE|MULTIPLE_CHOICE", message = "Тип вопроса должен быть SINGLE_CHOICE или MULTIPLE_CHOICE")
    private String type;
}
