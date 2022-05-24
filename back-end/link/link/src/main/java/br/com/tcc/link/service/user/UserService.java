package br.com.tcc.link.service.user;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.tag.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserTagService userTagService;

    //Método para inserção de um usuário no banco de dados, verificando se o email do mesmo já foi cadastrado,
    //se a confirmação de senha está idêntica a senha inserida. Também realiza a inserção de suas Tags no banco de dados
    public UserResponse create(final CreateUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessValidationException("Email já registrado na base.");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessValidationException("Confirmação de senha tem valor diferente de Senha.");
        }

        repository.save(mapper.toDomain(request));

        final User user = repository.findByEmail(request.getEmail());

        request.getUserTags().forEach(tag -> userTagService.save(tag, user.getId()));

        return mapper.toUserResponse(user, request.getUserTags());
    }

    //Método que retorna um User de acordo com o userId inserido, caso ele exista no banco de dados
    public User findById(final Integer userId){
        return repository.findUserById(userId);
    }

    //Método que retorna UserResponse de acordo com o userId inserido
    public UserResponse getUserById(Integer userId) {
        User user = repository.findUserById(userId);
        List<String> userTags = userTagService.findAllByUserId(userId);

        return mapper.toUserResponse(user, userTags);
    }
}
