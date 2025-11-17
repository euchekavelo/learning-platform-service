package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор модуля не может быть пустым")
    private Integer moduleId;

    @NotBlank(message = "Заголовок теста не может быть пустым")
    private String title;

    @Min(value = 0, message = "Лимит времени не может быть отрицательным")
    private Integer timeLimit;
}
