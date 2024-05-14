package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestRunDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestRunMapper {
    TestEntity toEntity(TestRunDto testRunDto);

    TestRunDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestRunDto testRunDto, @MappingTarget TestEntity testEntity);
}