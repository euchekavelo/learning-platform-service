package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AssignmentRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор урока не может быть пустым")
    private Integer lessonId;

    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @NotBlank(message = "Описание не может быть пустым")
    private String description;

    @NotNull(message = "Дата сдачи не может быть пустой")
    private Date dueDate;

    @Min(value = 0, message = "Максимальные баллы не могут быть отрицательными")
    private Integer maxScore;
}
