package by.yudzin.testingsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestsUserOptionDoneDto implements Serializable {

    Long qUserId;
    @JsonProperty("id")
    Long id_option;
    Boolean correct;
}