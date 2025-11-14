package ru.mephi.learningplatformservice.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.learningplatformservice.dto.request.UserRequestDto;
import ru.mephi.learningplatformservice.dto.response.UserResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.UserMapper;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileService profileService;

    @Transactional
    public UserResponseDto createUser(@Valid UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        profileService.createProfileForUser(savedUser);

        return userMapper.toUserResponseDto(savedUser);
    }

    public User getUserEntityById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с указанным ID не найден."));
    }

    public List<UserResponseDto> getStudentsByCourseId(Integer courseId) {
        return userRepository.getStudentsByCourseId(courseId).stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }
}
