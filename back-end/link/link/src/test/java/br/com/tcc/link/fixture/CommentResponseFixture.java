package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.Comment;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.user.UserCommentResponse;

public class CommentResponseFixture {

    public static CommentResponse makeRandomCommentResponse(final Comment comment,
                                                            final UserCommentResponse userCommentResponse) {
        return CommentResponse.builder()
                .id(comment.getId())
                .commentText(comment.getCommentText())
                .userResponse(userCommentResponse)
                .build();
    }
}
