package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.response.user.UserCommentResponse;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class UserCommentResponseFixture {
    public static UserCommentResponse makeRandomUserCommentResponse() {
        return UserCommentResponse.builder()
                .id(nextInt())
                .name(randomAlphabetic(10))
                .avatar(randomAlphabetic(10))
                .build();
    }
}
