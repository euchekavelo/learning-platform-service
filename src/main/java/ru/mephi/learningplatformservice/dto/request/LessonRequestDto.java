package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LessonRequestDto {

    private Integer moduleId;
    private List<LessonComponentRequestDto> newLessons;
}
