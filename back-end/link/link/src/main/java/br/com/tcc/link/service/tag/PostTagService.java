package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.PostTag;
import br.com.tcc.link.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostTagService {

    @Autowired
    private PostTagRepository repository;

    //Método para salvar PostTags no banco de dados, recebendo o nome da Tag e o id do Post a qual ela pertence
    public void save(final String postTag, final Integer postId) {
        PostTag postTags = new PostTag(postTag, postId);
        repository.save(postTags);
    }

    //Método que deleta todas as PostTags de acordo com o postId inserido
    public void deleteAllByPostId(final Integer postId) {
        List<PostTag> postTags = repository.findAllByPostId(postId);
        repository.deleteAll(postTags);
    }

    //Método que recebe um postId e retorna uma Lista de nomes de Tags associados a esse Post
    public List<String> findAllByPostId(final Integer postId) {
        return repository.findAllByPostId(postId)
                .stream()
                .map(PostTag::getTagName)
                .collect(Collectors.toList());
    }

    public List<PostTag> findAllByTagNameIn(final List<String> tagNames) {
        return repository.findAllByTagNameIn(tagNames);
    }
}
