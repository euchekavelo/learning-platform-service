package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LessonRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор модуля не может быть пустым")
    private Integer moduleId;

    @NotNull(message = "Список новых уроков не может быть пустым")
    private List<LessonComponentRequestDto> newLessons;
}
