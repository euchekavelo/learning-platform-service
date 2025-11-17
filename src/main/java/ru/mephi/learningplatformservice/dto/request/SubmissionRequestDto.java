package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmissionRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор задания не может быть пустым")
    private Integer assignmentId;

    @NotBlank(message = "Контент не может быть пустым")
    private String content;
}
