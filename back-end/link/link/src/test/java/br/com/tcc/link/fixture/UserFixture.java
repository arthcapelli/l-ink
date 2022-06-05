package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.User;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class UserFixture {

    public static User makeRandomUser() {
        String street = randomAlphabetic(10);
        String city = randomAlphabetic(10);
        String uf = randomAlphabetic(2);

        return User.builder()
                .id(nextInt())
                .name(randomAlphabetic(10))
                .email(randomAlphabetic(10))
                .password(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .isTattooArtist(nextBoolean())
                .location(String.format("%s-%s-%s", street, city, uf))
                .phone(randomAlphabetic(10))
                .build();
    }
}
