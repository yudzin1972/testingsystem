package by.yudzin.testingsystem.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Violation {

    private final String fieldName;
    private final String message;
    private final List<?> errors;
}

