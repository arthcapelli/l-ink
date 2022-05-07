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

    public void save(final String postTag, final Integer userId) {
        PostTag postTags = new PostTag(postTag, userId);
        repository.save(postTags);
    }

    public void deleteAllByPostId(final Integer postId) {
        List<PostTag> postTags = repository.findAllByPostId(postId);
        repository.deleteAll(postTags);
    }

    public List<String> findAllByPostId(final Integer postId) {
        return repository.findAllByPostId(postId)
                .stream()
                .map(PostTag::getTagName)
                .collect(Collectors.toList());
    }
}
