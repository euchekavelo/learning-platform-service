package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDto {

    @NotNull(message = "Идентификатор курс не может быть пустым")
    private Integer courseId;

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;
}
