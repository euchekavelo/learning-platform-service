package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class CourseRequestDto {

    private String title;
    private String description;
    private Integer categoryId;
    private Integer teacherId;
}
