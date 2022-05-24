package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.tcc.link.fixture.CreateUserRequestFixture.makeRandomCreateUserRequest;
import static br.com.tcc.link.fixture.UserFixture.makeRandomUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    public void createUserRequestToDomain() {
        CreateUserRequest userRequest = makeRandomCreateUserRequest();
        User response = mapper.toDomain(userRequest);

        assertEquals(userRequest.getName(), response.getName());
        assertEquals(userRequest.getPassword(), response.getPassword());
        assertEquals(userRequest.getEmail(), response.getEmail());
        assertEquals(userRequest.getAvatar(), response.getAvatar());
        assertEquals(userRequest.getIsTattooArtist(), response.getIsTattooArtist());
        assertEquals(userRequest.getExpTime(), response.getExpTime());
    }

    @Test
    public void userDomainToUserResponse() {
        User randomUser = makeRandomUser();
        List<String> userTags = List.of("Blackwork");
        UserResponse userResponse = mapper.toUserResponse(randomUser, userTags);

        assertEquals(userResponse.getId(), randomUser.getId());
        assertEquals(userResponse.getName(), randomUser.getName());
        assertEquals(userResponse.getId(), randomUser.getId());
        assertEquals(userResponse.getAvatar(), randomUser.getAvatar());
        assertEquals(userResponse.isTattooArtist(), randomUser.getIsTattooArtist());
        assertEquals(userResponse.getExpTime(), randomUser.getExpTime());
        assertEquals(userResponse.getUserTags(), userTags);
    }
}