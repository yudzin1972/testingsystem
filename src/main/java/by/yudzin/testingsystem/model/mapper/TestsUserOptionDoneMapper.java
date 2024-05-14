package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestsUserOptionDoneDto;
import by.yudzin.testingsystem.model.entity.TestsUserOptionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestsUserOptionDoneMapper {
    TestsUserOptionEntity toEntity(TestsUserOptionDoneDto testsUserOptionDoneDto);

    TestsUserOptionDoneDto toDto(TestsUserOptionEntity testsUserOptionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestsUserOptionEntity partialUpdate(TestsUserOptionDoneDto testsUserOptionDoneDto, @MappingTarget TestsUserOptionEntity testsUserOptionEntity);
}