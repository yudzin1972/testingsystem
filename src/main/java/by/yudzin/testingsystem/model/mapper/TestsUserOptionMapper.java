package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestsUserOptionDto;
import by.yudzin.testingsystem.model.entity.TestsUserOptionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestsUserOptionMapper {
    TestsUserOptionEntity toEntity(TestsUserOptionDto testsUserOptionDto);

    TestsUserOptionDto toDto(TestsUserOptionEntity testsUserOptionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestsUserOptionEntity partialUpdate(TestsUserOptionDto testsUserOptionDto, @MappingTarget TestsUserOptionEntity testsUserOptionEntity);
}