package dev.leandro.erllet.satella.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HomeType {

    PRIVATE("Privada"), PUBLIC("Pública"), TEMPORARY("Temporária"), DEFAULT("Padrão");

    private final String description;

}
