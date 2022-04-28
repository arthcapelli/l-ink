package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.request.user.CreateUserRequest;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;

public class CreateUserRequestFixture {

    public static CreateUserRequest makeRandomCreateUserRequest() {
        return CreateUserRequest.builder()
                .name(randomAlphabetic(10))
                .email(randomAlphabetic(10))
                .password(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .isTattooArtist(nextBoolean())
                .userTags(emptyList())
                .build();
    }

    public static CreateUserRequest makeValidCreateUserRequest() {
        final String password = randomAlphabetic(10);
        return CreateUserRequest.builder()
                .name(randomAlphabetic(10))
                .email(randomAlphabetic(10))
                .password(password)
                .confirmPassword(password)
                .avatar(randomAlphabetic(10))
                .isTattooArtist(nextBoolean())
                .userTags(emptyList())
                .build();
    }
}
