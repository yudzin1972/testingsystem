package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestPromRezDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestPromRezMapper {
    TestEntity toEntity(TestPromRezDto testPromRezDto);

    TestPromRezDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestPromRezDto testPromRezDto, @MappingTarget TestEntity testEntity);
}