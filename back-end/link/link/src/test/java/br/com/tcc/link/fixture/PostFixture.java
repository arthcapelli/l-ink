package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.Post;

import static br.com.tcc.link.fixture.LocalDateTimeFixture.japan2011Earthquake;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PostFixture {

    public static Post makeRandomPost(final Integer userId) {
        return Post.builder()
                .id(nextInt())
                .bodyLocal(randomAlphabetic(10))
                .postImg(randomAlphabetic(10))
                .measures(randomAlphabetic(10))
                .userId(userId)
                .createdAt(japan2011Earthquake())
                .build();
    }
}
