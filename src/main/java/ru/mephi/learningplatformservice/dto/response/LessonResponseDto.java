package ru.mephi.learningplatformservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LessonResponseDto {

    private int moduleId;
    private List<LessonComponentResponseDto> newLessons;
}
