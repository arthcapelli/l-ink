package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.StyleTags;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsService {

    //Método que retorna a descrição de todas as Tags presentes no Enum StyleTags
    public List<String> getAllTags() {
        return Arrays.stream(StyleTags.values())
                .map(StyleTags::getDescription)
                .collect(Collectors.toList());
    }
}
