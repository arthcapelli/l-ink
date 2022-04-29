package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.request.authentication.LoginRequest;
import br.com.tcc.link.representation.request.user.CreateUserRequest;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;

public class LoginRequestFixture {

    public static LoginRequest makeRandomLoginRequest() {
        return LoginRequest.builder()
                .email(randomAlphabetic(10))
                .password(randomAlphabetic(10))
                .build();
    }

}
