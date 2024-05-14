package by.yudzin.testingsystem.model.mapper;

import by.yudzin.testingsystem.model.entity.AccountEntity;
import by.yudzin.testingsystem.model.entity.SubjectEntity;
import by.yudzin.testingsystem.model.dto.TestAddDto;
import by.yudzin.testingsystem.model.entity.TestEntity;
import by.yudzin.testingsystem.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestAddMapper {
    @Mapping(source = "subjectId", target = "subject.id")
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "userId", target = "user.id")
    TestEntity toEntity(TestAddDto testAddDto);

    @InheritInverseConfiguration(name = "toEntity")
    TestAddDto toDto(TestEntity testEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "userId", target = "user")
    TestEntity partialUpdate(TestAddDto testAddDto, @MappingTarget TestEntity testEntity);

    default AccountEntity createAccountEntity(Long accountId) {
        if (accountId == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountId);
        return accountEntity;
    }
    default UserEntity createUserEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return userEntity;
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