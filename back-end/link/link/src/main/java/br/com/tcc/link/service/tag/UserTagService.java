package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.UserTag;
import br.com.tcc.link.repository.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserTagService {

    @Autowired
    private UserTagRepository repository;

    //Método para salvar UserTags no banco de dados, recebendo o nome da Tag e o id do User ao qual ela pertence
    public void save(final String userTag, final Integer userId) {
        UserTag userTags = new UserTag(userTag, userId);
        repository.save(userTags);
    }

    //Método que recebe um userId e retorna uma Lista de nomes de Tags associados a esse User
    public List<String> findAllByUserId(final Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(UserTag::getTagName)
                .collect(Collectors.toList());
    }

}
