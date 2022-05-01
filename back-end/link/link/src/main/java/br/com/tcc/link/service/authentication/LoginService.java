package br.com.tcc.link.service.authentication;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.exception.NotFoundException;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.authentication.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class LoginService {

    @Autowired
    private UserRepository repository;

    public String login(final LoginRequest request) {
        User user = repository.findByEmail(request.getEmail());

        if (isNull(user)) {
           throw new NotFoundException("Email não registrado na base.");
        }

        if (!request.getPassword().equals(user.getPassword())) {
            throw new BusinessValidationException("Credenciais inválidas.");
        }

        return "Autenticado";
    }
}