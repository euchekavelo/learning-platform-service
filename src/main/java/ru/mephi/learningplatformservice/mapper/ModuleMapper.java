package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.ModuleComponentRequestDto;
import ru.mephi.learningplatformservice.dto.request.ModuleRequestDto;
import ru.mephi.learningplatformservice.dto.response.ModuleComponentResponseDto;
import ru.mephi.learningplatformservice.model.Module;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModuleMapper {

    Module toModel(ModuleRequestDto moduleRequestDto);

    List<Module> toModels(List<ModuleComponentRequestDto> moduleComponents);

    List<ModuleComponentResponseDto> toModuleComponentResponseDtoList(List<Module> modules);
}
