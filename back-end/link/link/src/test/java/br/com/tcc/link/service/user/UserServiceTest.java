package br.com.tcc.link.service.user;

import br.com.tcc.link.domain.StyleTags;
import br.com.tcc.link.domain.User;
import br.com.tcc.link.exception.BusinessValidationException;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.UserRepository;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.service.tag.UserTagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Mock
    private UserTagService userTagService;

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
            verifyNoInteractions(mapper, userTagService);
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
            verifyNoInteractions(mapper, userTagService);
        }
    }

    @Test
    public void createUserWithSuccess() {

        List<String> userTags = Arrays.stream(StyleTags.values())
                .map(StyleTags::getDescription)
                .collect(Collectors.toList());

        final CreateUserRequest request = makeValidCreateUserRequest(userTags);

        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        when(mapper.toDomain(request)).thenReturn(user);
        when(repository.findByEmail(request.getEmail())).thenReturn(user);

        service.create(request);

        verify(repository).existsByEmail(request.getEmail());
        verify(repository).save(user);
        verify(mapper).toDomain(request);

        verify(repository).existsByEmail(request.getEmail());
        verify(repository).save(user);
        verify(mapper).toDomain(request);

        request.getUserTags().forEach(tag -> verify(userTagService).save(tag, user.getId()));
    }

    @Test
    public void findUserByIdWithSuccess() {
        when(repository.findUserById(user.getId())).thenReturn(user);

        User response = service.findById(user.getId());

        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getAvatar(), response.getAvatar());
        assertEquals(user.getExpTime(), response.getExpTime());
        assertEquals(user.getIsTattooArtist(), response.getIsTattooArtist());
        assertEquals(user.getPassword(), response.getPassword());
    }
}