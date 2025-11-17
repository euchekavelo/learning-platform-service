package ru.mephi.learningplatformservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.mephi.learningplatformservice.config.PostgreSQLContainerConfig;
import ru.mephi.learningplatformservice.dto.request.AssignmentRequestDto;
import ru.mephi.learningplatformservice.dto.request.SubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.AssignmentResponseDto;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PostgreSQLContainerConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubmissionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateSubmissionSuccess() throws Exception {
        AssignmentRequestDto assignmentRequestDto = new AssignmentRequestDto();
        assignmentRequestDto.setLessonId(1);
        assignmentRequestDto.setUserId(1);
        assignmentRequestDto.setTitle("Title");
        assignmentRequestDto.setDescription("Description");
        assignmentRequestDto.setDueDate(new Date());
        assignmentRequestDto.setMaxScore(10);

        MvcResult result = mockMvc.perform(post("/api/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assignmentRequestDto)))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        AssignmentResponseDto assignmentResponseDto = objectMapper.readValue(response, AssignmentResponseDto.class);

        SubmissionRequestDto submissionRequestDto = new SubmissionRequestDto();
        submissionRequestDto.setAssignmentId(assignmentResponseDto.getId());
        submissionRequestDto.setUserId(2);
        submissionRequestDto.setContent("test content");

        mockMvc.perform(post("/api/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(submissionRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createSubmissionDuplicateFailure() throws Exception {
        SubmissionRequestDto submissionRequestDto = new SubmissionRequestDto();
        submissionRequestDto.setAssignmentId(1);
        submissionRequestDto.setUserId(2);
        submissionRequestDto.setContent("test content");

        mockMvc.perform(post("/api/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(submissionRequestDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void createSubmissionEmptyAssignmentIdFailure() throws Exception {
        SubmissionRequestDto submissionRequestDto = new SubmissionRequestDto();
        submissionRequestDto.setAssignmentId(null);
        submissionRequestDto.setUserId(2);
        submissionRequestDto.setContent("test content");

        mockMvc.perform(post("/api/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(submissionRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createSubmissionEmptyUserIdFailure() throws Exception {
        SubmissionRequestDto submissionRequestDto = new SubmissionRequestDto();
        submissionRequestDto.setAssignmentId(1);
        submissionRequestDto.setUserId(null);
        submissionRequestDto.setContent("test content");

        mockMvc.perform(post("/api/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(submissionRequestDto)))
                .andExpect(status().isBadRequest());
    }
}
