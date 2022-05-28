package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.Comment;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class CommentFixture {

    public static Comment makeRandomComment(final Integer userId) {
        return Comment.builder()
                .id(nextInt())
                .commentText(randomAlphabetic(10))
                .userId(userId)
                .postId(nextInt())
                .build();
    }
}
