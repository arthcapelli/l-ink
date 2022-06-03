package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.post.PostPageResponse;
import br.com.tcc.link.representation.response.user.UserResponse;

import java.util.List;

import static br.com.tcc.link.fixture.LocalDateTimeFixture.japan2011Earthquake;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class PostPageResponseFixture {

    public static PostPageResponse makeRandomPostPageResponse(final UserResponse userResponse,
                                                              final List<String> postTags,
                                                              final Boolean isFavorite,
                                                              List<CommentResponse> commentResponses) {
        return PostPageResponse.builder()
                .id(nextInt())
                .postImg(randomAlphabetic(10))
                .bodyLocal(randomAlphabetic(10))
                .measures(randomAlphabetic(10))
                .userResponse(userResponse)
                .postTags(postTags)
                .isFavorite(isFavorite)
                .comments(commentResponses)
                .createdAt(japan2011Earthquake().toString())
                .build();
    }
}
