package ru.mephi.learningplatformservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleResponseDto {

    private Integer courseId;
    private List<ModuleComponentResponseDto> newModules;
}
