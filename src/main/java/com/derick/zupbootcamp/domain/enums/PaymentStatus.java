package com.derick.zupbootcamp.domain.enums;

public enum PaymentStatus {
    RECEIVED(1, "Recebido"),
    PROCESSED(2, "Processado"),
    INVALID(3, "Inválido");

    private int value;
    private String description;

    private PaymentStatus(int i, String description) {
        this.value = i;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (PaymentStatus value : PaymentStatus.values()) {
            if (cod.equals(value.getValue())) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid value");
    }
}
