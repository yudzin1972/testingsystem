package by.yudzin.testingsystem.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProtokolOptionResponseDto {
    private Long id_option;
    private String text;
    private String correct_option;
    private String correct_option_user;
}
