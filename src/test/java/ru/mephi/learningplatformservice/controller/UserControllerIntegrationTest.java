package ru.mephi.learningplatformservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.mephi.learningplatformservice.config.PostgreSQLContainerConfig;
import ru.mephi.learningplatformservice.dto.request.UserRequestDto;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PostgreSQLContainerConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUserSuccess() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("test_username" + System.currentTimeMillis());
        userRequestDto.setPassword("password123");
        userRequestDto.setRole("STUDENT");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateUserWithDuplicatePasswordFailure() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("test_username1" + System.currentTimeMillis());
        userRequestDto.setPassword("test");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void testCreateUserWithDuplicateUsernameFailure() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("test");
        userRequestDto.setPassword("test23");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void testGetListOfUserSubmissions() throws Exception {
        mockMvc.perform(get("/api/users/2/submissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThan(0)));
    }

    @Test
    void testGetListOfUserSubmissionsNotStudentFailure() throws Exception {
        mockMvc.perform(get("/api/users/1/submissions"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetListOfUserSubmissionsUserNotFoundFailure() throws Exception {
        mockMvc.perform(get("/api/users/23232/submissions"))
                .andExpect(status().isNotFound());
    }
}
