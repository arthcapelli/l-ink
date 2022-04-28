package br.com.tcc.link.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StyleTags {

    BLACKWORK("Blackwork"),
    OLD_SCHOOL("Old school");

    @Getter
    private final String description;
}
