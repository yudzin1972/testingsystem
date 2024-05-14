package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestStartDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestStartMapper {
    TestEntity toEntity(TestStartDto testStartDto);

    TestStartDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestStartDto testStartDto, @MappingTarget TestEntity testEntity);
}