package br.com.tcc.link.service.authentication;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.exception.NotFoundException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.authentication.LoginRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.tcc.link.fixture.LoginRequestFixture.makeRandomLoginRequest;
import static br.com.tcc.link.fixture.UserFixture.makeRandomUser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    User user;
    LoginRequest request;

    @Before
    public void setUp() {
        user = makeRandomUser();
        request = makeRandomLoginRequest();
    }

    @Test
    public void validateIfUserExistInDatabase() {

        try {
            service.login(request);
        } catch (NotFoundException ex) {
            assertEquals("Email não registrado na base.", ex.getMessage());
        } finally {
            verify(repository).findByEmail(request.getEmail());
            verifyNoInteractions(mapper);
        }
    }

    @Test
    public void validatePassword() {

        when(repository.findByEmail(request.getEmail())).thenReturn(user);

        try {
            service.login(request);
        } catch (BusinessValidationException ex) {
            assertEquals("Credenciais inválidas.", ex.getMessage());
        } finally {
            verify(repository).findByEmail(request.getEmail());
            verifyNoInteractions(mapper);
        }
    }

    @Test
    public void success() {

        User validUser = User.builder()
                .id(nextInt())
                .name(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .password(request.getPassword())
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(validUser.getId())
                .name(validUser.getName())
                .avatar(validUser.getAvatar())
                .build();

        when(repository.findByEmail(request.getEmail())).thenReturn(validUser);
        when(mapper.toUserResponse(validUser)).thenReturn(userResponse);

        UserResponse response = service.login(request);

        verify(repository).findByEmail(request.getEmail());
        verify(mapper).toUserResponse(validUser);

        assertEquals(validUser.getId(), response.getId());
        assertEquals(validUser.getName(), response.getName());
        assertEquals(validUser.getAvatar(), response.getAvatar());
    }
}