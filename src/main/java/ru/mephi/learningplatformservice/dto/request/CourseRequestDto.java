package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDto {

    @NotBlank(message = "Заголовок курса не должен быть пустым")
    private String title;

    @NotBlank(message = "Описание курса не должно быть пустым")
    private String description;

    @NotNull(message = "Идентификатор категории не должен быть пустым")
    private Integer categoryId;

    @NotNull(message = "Идентификатор преподавателя не должен быть пустым")
    private Integer teacherId;
}
