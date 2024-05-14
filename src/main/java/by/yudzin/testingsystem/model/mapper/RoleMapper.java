package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.entity.RoleEntity;
import by.yudzin.testingsystem.model.dto.RoleDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    RoleEntity toEntity(RoleDto roleDto);

    RoleDto toDto(RoleEntity roleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(RoleDto roleDto, @MappingTarget RoleEntity roleEntity);
}