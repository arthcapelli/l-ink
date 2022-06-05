package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Comment;
import br.com.tcc.link.representation.request.comment.CreateCommentRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.user.UserCommentResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.tcc.link.fixture.CommentFixture.makeRandomComment;
import static br.com.tcc.link.fixture.CreateCommentFixture.makeRandomCreateCommentRequest;
import static br.com.tcc.link.fixture.UserCommentResponseFixture.makeRandomUserCommentResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class CommentMapperTest {

    private CommentMapper mapper = new CommentMapper();

    @Test
    public void createCommentResponseToDomain() {
        CreateCommentRequest commentRequest = makeRandomCreateCommentRequest();
        Comment comment = mapper.toDomain(commentRequest);

        assertEquals(commentRequest.getUserId(), comment.getUserId());
        assertEquals(commentRequest.getPostId(), comment.getPostId());
        assertEquals(commentRequest.getCommentText(), comment.getCommentText());
    }

    @Test
    public void commentDomainToCommentResponse() {
        UserCommentResponse userResponse = makeRandomUserCommentResponse();
        Comment comment = makeRandomComment(1);

        CommentResponse response = mapper.toResponse(comment, userResponse);

        assertEquals(comment.getId(), response.getId());
        assertEquals(comment.getCommentText(), response.getCommentText());
        assertEquals(comment.getCreatedAt().toString(), response.getCreatedAt());
        assertEquals(userResponse.getId(), response.getUserResponse().getId());
        assertEquals(userResponse.getName(), response.getUserResponse().getName());
        assertEquals(userResponse.getAvatar(), response.getUserResponse().getAvatar());
    }
}