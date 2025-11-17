package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class LessonComponentResponseDto {

    private Integer id;
    private String title;
    private String content;
    private String videoUrl;
}
