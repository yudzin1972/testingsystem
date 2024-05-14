package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.OptionAddDto;
import by.yudzin.testingsystem.model.entity.OptionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionAddMapper {
    OptionEntity toEntity(OptionAddDto optionAddDto);

    OptionAddDto toDto(OptionEntity optionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OptionEntity partialUpdate(OptionAddDto optionAddDto, @MappingTarget OptionEntity optionEntity);
}