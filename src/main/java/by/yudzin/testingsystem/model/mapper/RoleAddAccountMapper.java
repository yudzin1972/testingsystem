package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.RoleAddAccountDto;
import by.yudzin.testingsystem.model.entity.RoleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleAddAccountMapper {
    RoleEntity toEntity(RoleAddAccountDto roleAddAccountDto);

    RoleAddAccountDto toDto(RoleEntity roleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(RoleAddAccountDto roleAddAccountDto, @MappingTarget RoleEntity roleEntity);
}