package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.SubjectAddDto;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectAddMapper {
    SubjectEntity toEntity(SubjectAddDto subjectAddDto);

    SubjectAddDto toDto(SubjectEntity subjectEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectEntity partialUpdate(SubjectAddDto subjectAddDto, @MappingTarget SubjectEntity subjectEntity);

    SubjectEntity updateWithNull(SubjectAddDto subjectAddDto, @MappingTarget SubjectEntity subjectEntity);
}