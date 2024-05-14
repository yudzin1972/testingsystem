package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.SubjectTestDto;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectTestMapper {
    SubjectEntity toEntity(SubjectTestDto subjectTestDto);

    SubjectTestDto toDto(SubjectEntity subjectEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectEntity partialUpdate(SubjectTestDto subjectTestDto, @MappingTarget SubjectEntity subjectEntity);
}