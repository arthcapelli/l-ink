package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.request.post.CreatePostRequest;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CreatePostRequestFixture {

    public static CreatePostRequest makeRandomCreatePostRequest(final Integer userId, final List<String> postTags) {
        return CreatePostRequest.builder()
                .postImg(randomAlphabetic(10))
                .bodyLocal(randomAlphabetic(10))
                .measures(randomAlphabetic(10))
                .userId(userId)
                .postTags(postTags)
                .build();
    }
}
