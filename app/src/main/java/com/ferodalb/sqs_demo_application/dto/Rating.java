package com.ferodalb.sqs_demo_application.dto;

import java.util.Arrays;

public enum Rating {
    VERY_POOR(1),
    POOR(2),
    AVERAGE(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    Rating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rating fromValue(int value){
        return Arrays.stream(Rating.values())
                        .filter(rating -> rating.getValue() == value)
                        .findFirst()
                        .orElseThrow(()-> new IllegalArgumentException("Invalid rating value: " + value));
    }
}
