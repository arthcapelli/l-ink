package br.com.tcc.link.service.authentication;

import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.exception.NotFoundException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.authentication.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public void create(final CreateUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new NotFoundException("Email já registrado na base.");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessValidationException("Confirmação de senha tem valor diferente de Senha.");
        }

        repository.save(mapper.toDomian(request));
    }
}
