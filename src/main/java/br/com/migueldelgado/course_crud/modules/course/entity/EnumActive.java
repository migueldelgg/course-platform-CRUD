package br.com.migueldelgado.course_crud.modules.course.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public enum EnumActive {

    @NotNull
    @NotBlank
    ATIVO(1),
    INATIVO(2);

    private int code;

    private EnumActive(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EnumActive valueOf(int code) {
        for (EnumActive value : EnumActive.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid EnumActive code.");
    }
}