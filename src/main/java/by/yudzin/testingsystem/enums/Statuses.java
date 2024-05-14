package by.yudzin.testingsystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Statuses {

    DONE("DONE"),
    DEL("DEL"),
    NEW("NEW");

    private final String value;

    private static final Map<String, Statuses> map = Arrays.stream(Statuses.values())
            .collect(Collectors.toMap(Statuses::toString, Function.identity()));

    public static Statuses getByString(String value) {
        return map.getOrDefault(value, NEW);
    }
}
