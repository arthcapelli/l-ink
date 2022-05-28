package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.request.comment.CreateCommentRequest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class CreateCommentFixture {

    public static CreateCommentRequest makeRandomCreateCommentRequest() {
        return CreateCommentRequest.builder()
                .commentText(randomAlphabetic(10))
                .userId(nextInt())
                .postId(nextInt())
                .build();
    }
}
