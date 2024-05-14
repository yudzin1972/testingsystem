package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.entity.AccountEntity;
import by.yudzin.testingsystem.model.dto.AccountDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    AccountEntity toEntity(AccountDto accountDto);

    AccountDto toDto(AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity partialUpdate(AccountDto accountDto, @MappingTarget AccountEntity accountEntity);
}