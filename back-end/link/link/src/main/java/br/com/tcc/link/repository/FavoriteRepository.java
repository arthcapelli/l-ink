package br.com.tcc.link.repository;

import br.com.tcc.link.domain.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    Integer countByPostId(Integer idPost);

    List<Favorite> getAllByUserId(Integer idUser);

    void deleteByPostId(Integer idPost);

    boolean existsByUserIdAndPostId(Integer userId, Integer postId);

    void deleteByUserIdAndPostId(Integer userId, Integer postId);
}
