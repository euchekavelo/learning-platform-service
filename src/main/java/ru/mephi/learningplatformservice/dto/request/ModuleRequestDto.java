package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ModuleRequestDto {

    private Integer courseId;
    private List<ModuleComponentRequestDto> moduleComponents;
}
