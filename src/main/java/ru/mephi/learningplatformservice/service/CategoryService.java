package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.CategoryRequestDto;
import ru.mephi.learningplatformservice.dto.response.CategoryResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.CategoryMapper;
import ru.mephi.learningplatformservice.model.Category;
import ru.mephi.learningplatformservice.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserService userService;

    public Category getCategoryEntityById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория с указанным идентификатором не найдена."));
    }

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        userService.checkUserIsAdminOrTeacher(categoryRequestDto.getUserId());
        Category category = categoryMapper.toCategory(categoryRequestDto);

        return categoryMapper.toCategoryResponseDto(categoryRepository.save(category));
    }
}
