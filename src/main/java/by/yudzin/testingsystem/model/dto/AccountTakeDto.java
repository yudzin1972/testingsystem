package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.AccountEntity}
 */
@Value
public class AccountTakeDto implements Serializable {
    Long id;
    String userName;
}