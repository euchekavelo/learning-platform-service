package ru.mephi.learningplatformservice.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {

    private String username;
    private String password;
    private String role;
}
