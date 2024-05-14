package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.UserTeacherDto;
import by.yudzin.testingsystem.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserTeacherMapper {
    UserEntity toEntity(UserTeacherDto userTeacherDto);

    UserTeacherDto toDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserTeacherDto userTeacherDto, @MappingTarget UserEntity userEntity);
}