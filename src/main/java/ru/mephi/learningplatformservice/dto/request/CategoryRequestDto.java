package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotBlank(message = "Название категории не может быть пустым")
    private String name;
}
