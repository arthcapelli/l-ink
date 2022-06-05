package br.com.tcc.link.repository;

import br.com.tcc.link.domain.PostTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends CrudRepository<PostTag, Integer> {

    List<PostTag> findAllByPostId(Integer idPost);
    List<PostTag> findAllByTagNameIn(List<String> tagNames);
}
