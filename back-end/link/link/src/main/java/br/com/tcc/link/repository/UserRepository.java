package br.com.tcc.link.repository;

import br.com.tcc.link.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
