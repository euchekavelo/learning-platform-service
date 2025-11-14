package ru.mephi.learningplatformservice.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.ModuleRequestDto;
import ru.mephi.learningplatformservice.dto.response.ModuleComponentResponseDto;
import ru.mephi.learningplatformservice.dto.response.ModuleResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.ModuleMapper;
import ru.mephi.learningplatformservice.model.Course;
import ru.mephi.learningplatformservice.model.Module;
import ru.mephi.learningplatformservice.repository.ModuleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseService courseService;
    private final ModuleMapper moduleMapper;

    public ModuleResponseDto addModules(@Valid ModuleRequestDto moduleRequestDto) {
        if (moduleRequestDto.getModuleComponents().isEmpty()) {
            throw new UnsupportedOperationException("Отсутствует информация о модулях курса.");
        }

        Course findedCourse = courseService.getCourseEntityById(moduleRequestDto.getCourseId());

        List<Module> modules = moduleMapper.toModels(moduleRequestDto.getModuleComponents());
        modules.forEach(module -> module.setCourse(findedCourse));
        List<Module> savedModules = moduleRepository.saveAll(modules);
        List<ModuleComponentResponseDto> componentResponseDtoList
                = moduleMapper.toModuleComponentResponseDtoList(savedModules);

        return ModuleResponseDto.builder()
                .courseId(findedCourse.getId())
                .newModules(componentResponseDtoList)
                .build();
    }

    public Module getModuleEntityById(Integer id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Модуль с указанным ID не найден."));
    }
}
