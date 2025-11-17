package ru.mephi.learningplatformservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ModuleRequestDto {

    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    private Integer userId;

    @NotNull(message = "Идентификатор курса не может быть пустым")
    private Integer courseId;

    @NotNull(message = "Составляющие модуля не могут быть пустыми")
    private List<ModuleComponentRequestDto> moduleComponents;
}
