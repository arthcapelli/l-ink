package br.com.tcc.link.repository;

import br.com.tcc.link.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findPostById(Integer postId);
}
