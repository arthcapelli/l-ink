package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PostResponseFixture {

    public static PostResponse makeRandomPostResponse(final UserResponse userResponse, final List<String> postTags, final Boolean isFavorite) {
        return PostResponse.builder()
                .id(nextInt())
                .postImg(randomAlphabetic(10))
                .bodyLocal(randomAlphabetic(10))
                .measures(randomAlphabetic(10))
                .userResponse(userResponse)
                .postTags(postTags)
                .isFavorite(isFavorite)
                .build();
    }
}
