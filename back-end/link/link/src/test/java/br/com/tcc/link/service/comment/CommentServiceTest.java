package br.com.tcc.link.service.comment;

import br.com.tcc.link.domain.Comment;
import br.com.tcc.link.domain.User;
import br.com.tcc.link.mapper.CommentMapper;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.CommentRepository;
import br.com.tcc.link.representation.request.comment.CreateCommentRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.user.UserCommentResponse;
import br.com.tcc.link.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.tcc.link.fixture.CommentFixture.makeRandomComment;
import static br.com.tcc.link.fixture.CommentResponseFixture.makeRandomCommentResponse;
import static br.com.tcc.link.fixture.CreateCommentFixture.makeRandomCreateCommentRequest;
import static br.com.tcc.link.fixture.UserCommentResponseFixture.makeRandomUserCommentResponse;
import static br.com.tcc.link.fixture.UserFixture.makeRandomUser;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CommentServiceTest {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentMapper mapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CommentRepository repository;

    @Mock
    private UserService userService;

    User user;
    UserCommentResponse userCommentResponse;
    Comment comment;
    CreateCommentRequest request;
    CommentResponse commentResponse;

    @Before
    public void setUp() {
        user = makeRandomUser();
        userCommentResponse = makeRandomUserCommentResponse();
        comment = makeRandomComment(userCommentResponse.getId());
        request = makeRandomCreateCommentRequest();
        commentResponse = makeRandomCommentResponse(comment, userCommentResponse);
    }

    @Test
    public void createCommentWithSuccess() {
        when(mapper.toDomain(request)).thenReturn(comment);

        String response = service.createComment(request);

        verify(repository).save(comment);
        assertEquals("Comentário criado!", response);
    }

    @Test
    public void returnCommentResponsesByPostWithSuccess(){
        List<Comment> commentList = asList(comment);

        when(repository.findAllByPostId(comment.getPostId())).thenReturn(commentList);
        when(userService.findById(comment.getUserId())).thenReturn(user);
        when(userMapper.toCommentResponse(user)).thenReturn(userCommentResponse);
        when(mapper.toResponse(comment, userCommentResponse)).thenReturn(commentResponse);

        List<CommentResponse> response = service.getPostComments(comment.getPostId());

        assertEquals(response.get(0).getId(), comment.getId());
        assertEquals(response.get(0).getCommentText(), comment.getCommentText());
        assertEquals(response.get(0).getUserResponse().getId(), userCommentResponse.getId());
        assertEquals(response.get(0).getUserResponse().getAvatar(), userCommentResponse.getAvatar());
        assertEquals(response.get(0).getUserResponse().getName(), userCommentResponse.getName());
    }
}