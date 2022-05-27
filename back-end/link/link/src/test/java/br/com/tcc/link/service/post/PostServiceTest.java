package br.com.tcc.link.service.post;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.domain.StyleTags;
import br.com.tcc.link.domain.User;
import br.com.tcc.link.mapper.PostMapper;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.PostRepository;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.favorite.FavoriteService;
import br.com.tcc.link.service.tag.PostTagService;
import br.com.tcc.link.service.tag.UserTagService;
import br.com.tcc.link.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tcc.link.fixture.CreatePostRequestFixture.makeRandomCreatePostRequest;
import static br.com.tcc.link.fixture.PostFixture.makeRandomPost;
import static br.com.tcc.link.fixture.PostResponseFixture.makeRandomPostResponse;
import static br.com.tcc.link.fixture.UserFixture.makeRandomUser;
import static br.com.tcc.link.fixture.UserResponseFixture.makeRandomUserResponse;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @InjectMocks
    private PostService service;

    @Mock
    private PostRepository repository;

    @Mock
    private PostMapper mapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PostTagService postTagService;

    @Mock
    private UserTagService userTagService;

    @Mock
    private UserService userService;

    @Mock
    private FavoriteService favoriteService;

    User user;
    Post post;
    CreatePostRequest createPostRequest;
    PostResponse postResponse;
    UserResponse userResponse;
    List<String> postTags;

    @Before
    public void setUp() {
        user = makeRandomUser();

        post = makeRandomPost(user.getId());

        List<String> userTags = List.of("Blackwork");
        userResponse = makeRandomUserResponse(userTags);

        postTags = Arrays.stream(StyleTags.values())
                .map(StyleTags::getDescription)
                .collect(Collectors.toList());

        postResponse = makeRandomPostResponse(userResponse, postTags, Boolean.TRUE);

        createPostRequest = makeRandomCreatePostRequest(user.getId(), postTags);
    }

    @Test
    public void createPostWithSuccess() {
        when(mapper.toDomain(createPostRequest)).thenReturn(post);

        String response = service.create(createPostRequest);

        verify(repository).save(post);

        createPostRequest.getPostTags().forEach(tag -> verify(postTagService).save(tag, post.getId()));
        assertEquals("Post criado!", response);
    }

    @Test
    public void deletePostWithSuccess() {
        when(repository.findPostById(post.getId())).thenReturn(post);

        service.delete(post.getId());

        verify(postTagService).deleteAllByPostId(post.getId());
        verify(repository).findPostById(post.getId());
        verify(repository).delete(post);
    }

    @Test
    public void returnAllPostsResponsesWithSuccess() {
        Integer authUserId = nextInt();
        List<Post> postList = asList(post);
        List<String> userTags = List.of("Blackwork");

        when(repository.findAllByOrderByIdDesc()).thenReturn(postList);
        when(userService.findById(post.getUserId())).thenReturn(user);
        when(userTagService.findAllByUserId(user.getId())).thenReturn(userTags);
        when(userMapper.toUserResponse(user, userTags)).thenReturn(userResponse);
        when(postTagService.findAllByPostId(post.getId())).thenReturn(postTags);
        when(favoriteService.existsFavorite(authUserId, post.getId())).thenReturn(Boolean.TRUE);
        when(mapper.toPostResponse(post, postTags, userResponse, Boolean.TRUE)).thenReturn(postResponse);

        List<PostResponse> response = service.getAllPosts(authUserId);

        assertEquals(postResponse.getId(), response.get(0).getId());
        assertEquals(postResponse.getPostImg(), response.get(0).getPostImg());
        assertEquals(postResponse.getMeasures(), response.get(0).getMeasures());
        assertEquals(postResponse.getUserResponse().getId(), response.get(0).getUserResponse().getId());
        for (int i = 0; i < postTags.size(); i++) {
            assertEquals(postTags.get(i), response.get(0).getPostTags().get(i));
        }
        assertEquals(postResponse.getIsFavorite(), response.get(0).getIsFavorite());
    }

    @Test
    public void verifyPostExistsByIdWithSuccess() {
        when(repository.existsById(post.getId())).thenReturn(true);

        boolean response = service.existsById(post.getId());

        assertTrue(response);
    }
}
