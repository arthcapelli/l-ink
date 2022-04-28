package br.com.tcc.link.representation.request.tag;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateUserTagRequest {

    private List<String> tagName = new ArrayList<>();

    private Integer idUser;
}
