package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.User;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class UserFixture {

    public static User makeRandomUser() {
        return User.builder()
                .id(nextInt())
                .name(randomAlphabetic(10))
                .email(randomAlphabetic(10))
                .password(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .isTattooArtist(nextBoolean())
                .build();
    }
}