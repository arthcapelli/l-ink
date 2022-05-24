package br.com.tcc.link.service.authentication;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.exception.NotFoundException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.authentication.LoginRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.tag.UserTagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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
    private UserTagService userTagService;

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

        List<String> userTags = List.of("Blackwork");
        UserResponse userResponse = UserResponse.builder()
                .id(validUser.getId())
                .name(validUser.getName())
                .avatar(validUser.getAvatar())
                .userTags(userTags)
                .build();

        when(repository.findByEmail(request.getEmail())).thenReturn(validUser);
        when(userTagService.findAllByUserId(validUser.getId())).thenReturn(userTags);
        when(mapper.toUserResponse(validUser, userTags)).thenReturn(userResponse);

        UserResponse response = service.login(request);

        verify(repository).findByEmail(request.getEmail());
        verify(userTagService).findAllByUserId(validUser.getId());
        verify(mapper).toUserResponse(validUser, userTags);

        assertEquals(validUser.getId(), response.getId());
        assertEquals(validUser.getName(), response.getName());
        assertEquals(validUser.getAvatar(), response.getAvatar());
    }
}