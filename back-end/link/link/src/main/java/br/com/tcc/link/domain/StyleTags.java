package br.com.tcc.link.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StyleTags {

    BLACKWORK("Blackwork"),
    OLD_SCHOOL("Old School"),
    FINELINE("Fine Line"),
    AQUARELA("Aquarela"),
    PONTILISM("Pontilismo"),
    MINIMALISM("Minimalista"),
    GEOMETRIC("Geometrica"),
    SINGLE_LINE("Single Line"),
    ORIENTAL("Oriental"),
    REALISTIC("Realista"),
    MAORI("Maori"),
    THREE_DIMENSIONAL("3D"),
    ANIMAL("Animal"),
    NATURE("Nature"),
    LETTERING("Lettering");

    @Getter
    private final String description;
}
