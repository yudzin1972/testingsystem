package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestHeaderDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestHeaderMapper {
    TestEntity toEntity(TestHeaderDto testHeaderDto);

    TestHeaderDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestEntity partialUpdate(TestHeaderDto testHeaderDto, @MappingTarget TestEntity testEntity);
}