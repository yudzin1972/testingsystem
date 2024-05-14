package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.AccountAdminDto;
import by.yudzin.testingsystem.model.entity.AccountEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountAdminMapper {
    AccountEntity toEntity(AccountAdminDto accountAdminDto);

    AccountAdminDto toDto(AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity partialUpdate(AccountAdminDto accountAdminDto, @MappingTarget AccountEntity accountEntity);
}