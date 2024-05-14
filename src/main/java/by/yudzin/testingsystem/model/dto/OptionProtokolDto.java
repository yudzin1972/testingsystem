package by.yudzin.testingsystem.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link by.yudzin.testingsystem.model.entity.OptionEntity}
 */
@Value
public class OptionProtokolDto implements Serializable {
    Long id;
    String text_opt;
    boolean correct;
}