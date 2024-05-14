package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.dto.TestTakeDto;
import by.yudzin.testingsystem.model.entity.AccountEntity;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import by.yudzin.testingsystem.model.entity.TestEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestTakeMapper {
    @Mapping(source = "subjectId", target = "subject.id")
    @Mapping(source = "accountId", target = "account.id")
    TestEntity toEntity(TestTakeDto testTakeDto);

    @InheritInverseConfiguration(name = "toEntity")
    TestTakeDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "accountId", target = "account")
    TestEntity partialUpdate(TestTakeDto testTakeDto, @MappingTarget TestEntity testEntity);

    default AccountEntity createAccountEntity(Long accountId) {
        if (accountId == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountId);
        return accountEntity;
    }

    default SubjectEntity createSubjectEntity(Long subjectId) {
        if (subjectId == null) {
            return null;
        }
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectId);
        return subjectEntity;
    }
}