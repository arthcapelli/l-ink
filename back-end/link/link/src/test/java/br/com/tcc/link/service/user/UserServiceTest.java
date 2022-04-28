package br.com.tcc.link.service.user;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.tcc.link.fixture.CreateUserRequestFixture.makeRandomCreateUserRequest;
import static br.com.tcc.link.fixture.CreateUserRequestFixture.makeValidCreateUserRequest;
import static br.com.tcc.link.fixture.UserFixture.makeRandomUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    User user;
    CreateUserRequest request;

    @Before
    public void setUp() {
        user = makeRandomUser();
        request = makeRandomCreateUserRequest();
    }

    @Test
    public void validateUserAlreadyExistingInDatabase() {
        when(repository.existsByEmail(request.getEmail())).thenReturn(true);

        try {
            service.create(request);
        } catch (BusinessValidationException ex) {
            assertEquals("Email já registrado na base.", ex.getMessage());
        } finally {
            verify(repository).existsByEmail(request.getEmail());
            verifyNoInteractions(mapper);
        }
    }

    @Test
    public void validatePassword() {

        when(repository.existsByEmail(request.getEmail())).thenReturn(false);


        try {
            service.create(request);
        } catch (BusinessValidationException ex) {
            assertEquals("Confirmação de senha tem valor diferente de Senha.", ex.getMessage());
        } finally {
            verify(repository).existsByEmail(request.getEmail());
            verifyNoInteractions(mapper);
        }
    }

    @Test
    public void success() {

        final CreateUserRequest request = makeValidCreateUserRequest();

        when(repository.existsByEmail(request.getEmail())).thenReturn(false);

        service.create(request);

        verify(repository).existsByEmail(request.getEmail());
        verify(mapper).toDomain(request);

    }

}