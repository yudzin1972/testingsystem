package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.AccountAddDto;
import by.yudzin.testingsystem.model.entity.AccountEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountAddMapper {
    AccountEntity toEntity(AccountAddDto accountAddDto);

    AccountAddDto toDto(AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AccountEntity partialUpdate(AccountAddDto accountAddDto, @MappingTarget AccountEntity accountEntity);
}