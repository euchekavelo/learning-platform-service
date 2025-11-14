package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class LessonComponentRequestDto {

    private String title;
    private String content;
    private String videoUrl;
}
