package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluateRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 5, message = "Оценка не может быть больше 5")
    private Integer score;

    @NotBlank(message = "Фидбек не может быть пустым")
    private String feedback;
}
