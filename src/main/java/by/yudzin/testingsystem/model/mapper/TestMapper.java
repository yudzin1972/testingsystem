package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestMapper {
    TestEntity toEntity(TestDto testDto);

    TestDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestDto testDto, @MappingTarget TestEntity testEntity);
}