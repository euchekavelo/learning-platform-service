package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class AssignmentRequestDto {

    private Integer userId;
    private Integer lessonId;
    private String title;
    private String description;
    private Date dueDate;
    private Integer maxScore;
}
