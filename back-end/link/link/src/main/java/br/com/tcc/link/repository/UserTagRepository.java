package br.com.tcc.link.repository;

import br.com.tcc.link.domain.UserTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTagRepository extends CrudRepository<UserTag, Integer> {

    List<UserTag> findAllByUserId(Integer idUser);
}
