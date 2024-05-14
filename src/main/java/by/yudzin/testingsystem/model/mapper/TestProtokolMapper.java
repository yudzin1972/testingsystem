package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestProtokolDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestProtokolMapper {
    TestEntity toEntity(TestProtokolDto testProtokolDto);

    TestProtokolDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestProtokolDto testProtokolDto, @MappingTarget TestEntity testEntity);
}