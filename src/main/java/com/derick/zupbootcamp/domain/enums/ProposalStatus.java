package com.derick.zupbootcamp.domain.enums;

public enum ProposalStatus {
    WAITING(1, "Aguardando resposta"),
    ACCEPTED(2, "Aceita"),
    APPROVED(3, "Aprovada"),
    DECLINED(4, "Recusada");

    private int value;
    private String description;

    private ProposalStatus(int i, String description) {
        this.value = i;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return description;
    }

    public static ProposalStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (ProposalStatus value : ProposalStatus.values()) {
            if (cod.equals(value.getValue())) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid value");
    }
}
