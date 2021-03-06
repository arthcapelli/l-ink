package br.com.tcc.link.repository;

import br.com.tcc.link.domain.UserTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTagRepository extends CrudRepository<UserTag, Integer> {

    List<UserTag> findAllByUserId(Integer idUser);
}
