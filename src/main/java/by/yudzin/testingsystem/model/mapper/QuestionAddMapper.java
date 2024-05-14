package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.QuestionAddDto;
import by.yudzin.testingsystem.model.entity.QuestionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionAddMapper {
    QuestionEntity toEntity(QuestionAddDto questionAddDto);

    QuestionAddDto toDto(QuestionEntity questionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QuestionEntity partialUpdate(QuestionAddDto questionAddDto, @MappingTarget QuestionEntity questionEntity);
}