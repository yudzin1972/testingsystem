package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.QuestionDto;
import by.yudzin.testingsystem.model.entity.QuestionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    QuestionEntity toEntity(QuestionDto questionDto);

    QuestionDto toDto(QuestionEntity questionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QuestionEntity partialUpdate(QuestionDto questionDto, @MappingTarget QuestionEntity questionEntity);
}