package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.CategoryRequestDto;
import ru.mephi.learningplatformservice.dto.response.CategoryResponseDto;
import ru.mephi.learningplatformservice.model.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toCategoryResponseDto(Category category);
}
