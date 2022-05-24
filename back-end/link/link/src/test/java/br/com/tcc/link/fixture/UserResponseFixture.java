package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.response.user.UserResponse;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class UserResponseFixture {

    public static UserResponse makeRandomUserResponse(final List<String> userTags) {
        return UserResponse.builder()
                .id(nextInt())
                .name(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .userTags(userTags)
                .build();
    }
}
