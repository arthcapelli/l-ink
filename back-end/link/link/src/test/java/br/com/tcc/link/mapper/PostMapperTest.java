package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.tcc.link.fixture.CreatePostRequestFixture.makeRandomCreatePostRequest;
import static br.com.tcc.link.fixture.PostFixture.makeRandomPost;
import static br.com.tcc.link.fixture.UserResponseFixture.makeRandomUserResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<String> postTags = List.of("Blackwork");
        UserResponse userResponse = makeRandomUserResponse();
        Integer userId = userResponse.getId();

        Post post = makeRandomPost(userId);
        PostResponse postResponse = mapper.toPostResponse(post, postTags, userResponse);

        assertEquals(postResponse.getId(), post.getId());
        assertEquals(postResponse.getPostImg(), post.getPostImg());
        assertEquals(postResponse.getBodyLocal(), post.getBodyLocal());
        assertEquals(postResponse.getMeasures(), post.getMeasures());
        assertEquals(postResponse.getUserResponse().getId(), post.getUserId());
        assertEquals(postResponse.getPostTags(), postTags);

    }
}
