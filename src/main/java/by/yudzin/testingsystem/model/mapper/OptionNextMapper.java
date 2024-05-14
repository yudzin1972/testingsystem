package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.OptionNextDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionNextMapper {
    OptionEntity toEntity(OptionNextDto optionNextDto);

    OptionNextDto toDto(OptionEntity optionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OptionEntity partialUpdate(OptionNextDto optionNextDto, @MappingTarget OptionEntity optionEntity);
}