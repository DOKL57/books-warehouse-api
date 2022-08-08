package com.dokl57.simplewarehouseapi.enums;

import org.apache.logging.log4j.util.Strings;

import java.util.stream.Stream;

public enum Operation {
    LESS_THAN("<"),
    GREATER_THAN(">"),
    EQUAL("="),
    EMPTY(Strings.EMPTY);

    private final String code;

    Operation(String code) {
        this.code = code;
    }

    public static Operation decode(String code){
        return Stream.of(
                Operation.values()
        ).filter(
                operation -> operation.code.equals(code)
        ).findFirst().orElse(EMPTY);
    }
}

