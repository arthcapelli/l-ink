package br.com.tcc.link.service.user;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.service.tag.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserTagService userTagService;

    public void create(final CreateUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessValidationException("Email já registrado na base.");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessValidationException("Confirmação de senha tem valor diferente de Senha.");
        }

        repository.save(mapper.toDomain(request));

        final User user = repository.findByEmail(request.getEmail());

        request.getUserTags().forEach(tag -> userTagService.save(tag, user.getId()));
    }

    public User findById(final Integer userId){
        return repository.findUserById(userId);
    }
}
