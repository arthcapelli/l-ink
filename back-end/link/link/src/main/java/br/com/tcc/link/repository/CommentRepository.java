package br.com.tcc.link.repository;

import br.com.tcc.link.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAllByPostId(Integer postId);
}
