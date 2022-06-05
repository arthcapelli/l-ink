package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Comment;
import br.com.tcc.link.domain.Post;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.post.PostPageResponse;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserCommentResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.tcc.link.fixture.CommentFixture.makeRandomComment;
import static br.com.tcc.link.fixture.CommentResponseFixture.makeRandomCommentResponse;
import static br.com.tcc.link.fixture.CreatePostRequestFixture.makeRandomCreatePostRequest;
import static br.com.tcc.link.fixture.PostFixture.makeRandomPost;
import static br.com.tcc.link.fixture.UserCommentResponseFixture.makeRandomUserCommentResponse;
import static br.com.tcc.link.fixture.UserResponseFixture.makeRandomUserResponse;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PostMapperTest {

    private PostMapper mapper = new PostMapper();

    @Test
    public void createPostRequestToDomain() {
        Integer userId = 1;
        List<String> postTags = List.of("Blackwork");

        CreatePostRequest postRequest = makeRandomCreatePostRequest(userId, postTags);
        Post response = mapper.toDomain(postRequest);

        assertEquals(postRequest.getPostImg(), response.getPostImg());
        assertEquals(postRequest.getBodyLocal(), response.getBodyLocal());
        assertEquals(postRequest.getMeasures(), response.getMeasures());
        assertEquals(postRequest.getUserId(), response.getUserId());
    }

    @Test
    public void postDomainToPostResponse() {
        List<String> tags = List.of("Blackwork");
        UserResponse userResponse = makeRandomUserResponse(tags);
        Integer userId = userResponse.getId();

        Post post = makeRandomPost(userId);
        PostResponse postResponse = mapper.toPostResponse(post, tags, userResponse, Boolean.TRUE);

        assertEquals(post.getId(), postResponse.getId());
        assertEquals(post.getPostImg(), postResponse.getPostImg());
        assertEquals(post.getBodyLocal(), postResponse.getBodyLocal());
        assertEquals(post.getMeasures(), postResponse.getMeasures());
        assertEquals(post.getUserId(), postResponse.getUserResponse().getId());
        assertEquals(post.getUserId(), postResponse.getUserResponse().getId());
        assertEquals(post.getCreatedAt().toString(), postResponse.getCreatedAt());
        assertEquals(tags, postResponse.getPostTags());
        assertEquals(userResponse.getId(), postResponse.getUserResponse().getId());
        assertEquals(userResponse.getName(), postResponse.getUserResponse().getName());
        assertEquals(userResponse.getAvatar(), postResponse.getUserResponse().getAvatar());
        assertEquals(userResponse.getExpTime(), postResponse.getUserResponse().getExpTime());
        assertEquals(userResponse.getUserTags(), postResponse.getUserResponse().getUserTags());
        assertEquals(userResponse.getStreet(), postResponse.getUserResponse().getStreet());
        assertEquals(userResponse.getCity(), postResponse.getUserResponse().getCity());
        assertEquals(userResponse.getUf(), postResponse.getUserResponse().getUf());
        assertEquals(userResponse.getPhone(), postResponse.getUserResponse().getPhone());
        assertTrue(postResponse.getIsFavorite());
    }

    @Test
    public void postDomainToPostPageResponse() {
        List<String> tags = List.of("Blackwork");
        UserResponse userResponse = makeRandomUserResponse(tags);
        Integer userId = userResponse.getId();
        UserCommentResponse userCommentResponse = makeRandomUserCommentResponse();
        Comment comment = makeRandomComment(userId);
        List<CommentResponse> commentResponses = asList(makeRandomCommentResponse(comment, userCommentResponse));

        Post post = makeRandomPost(userId);

        PostPageResponse postPageResponse = mapper.toPostPageResponse(post, tags, userResponse, true, commentResponses);

        assertEquals(post.getId(), postPageResponse.getId());
        assertEquals(post.getPostImg(), postPageResponse.getPostImg());
        assertEquals(post.getBodyLocal(), postPageResponse.getBodyLocal());
        assertEquals(post.getMeasures(), postPageResponse.getMeasures());
        assertEquals(post.getUserId(), postPageResponse.getUserResponse().getId());
        assertEquals(post.getCreatedAt().toString(), postPageResponse.getCreatedAt());
        assertEquals(tags, postPageResponse.getPostTags());
        assertEquals(userResponse.getId(), postPageResponse.getUserResponse().getId());
        assertEquals(userResponse.getName(), postPageResponse.getUserResponse().getName());
        assertEquals(userResponse.getAvatar(), postPageResponse.getUserResponse().getAvatar());
        assertEquals(userResponse.getExpTime(), postPageResponse.getUserResponse().getExpTime());
        assertEquals(userResponse.getUserTags(), postPageResponse.getUserResponse().getUserTags());
        assertEquals(userResponse.getStreet(), postPageResponse.getUserResponse().getStreet());
        assertEquals(userResponse.getCity(), postPageResponse.getUserResponse().getCity());
        assertEquals(userResponse.getUf(), postPageResponse.getUserResponse().getUf());
        assertEquals(userResponse.getPhone(), postPageResponse.getUserResponse().getPhone());
        assertEquals(commentResponses, postPageResponse.getComments());
        assertTrue(postPageResponse.getIsFavorite());
    }
}
