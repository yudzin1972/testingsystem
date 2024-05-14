package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestsHistoryQuestionDto;
import by.yudzin.testingsystem.model.entity.TestsHistoryQuestionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestsHistoryQuestionMapper {
    TestsHistoryQuestionEntity toEntity(TestsHistoryQuestionDto testsHistoryQuestionDto);

//    @AfterMapping
//    default void linkTestsUserOptionEntities(@MappingTarget TestsHistoryQuestionEntity testsHistoryQuestionEntity) {
//        testsHistoryQuestionEntity.getTestsUserOptionEntities().forEach(testsUserOptionEntity -> testsUserOptionEntity.setQUser(testsHistoryQuestionEntity));
//    }

    TestsHistoryQuestionDto toDto(TestsHistoryQuestionEntity testsHistoryQuestionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TestsHistoryQuestionEntity partialUpdate(TestsHistoryQuestionDto testsHistoryQuestionDto, @MappingTarget TestsHistoryQuestionEntity testsHistoryQuestionEntity);
}