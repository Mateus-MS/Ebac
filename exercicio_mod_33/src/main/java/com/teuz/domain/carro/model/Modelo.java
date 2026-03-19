package com.teuz.domain.carro.model;

public enum Modelo {
    SUV("suv"),
    HATCHBACK("hatchbacl"),
    SEDAN("sedan"),
    PICAPE("picape");

    private final String type;

    Modelo(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
