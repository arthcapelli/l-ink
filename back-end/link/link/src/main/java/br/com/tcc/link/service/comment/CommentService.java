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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    //Método que salva o comentário no banco e retorna uma string de confirmação
    public String createComment(CreateCommentRequest request) {
        Comment comment = mapper.toDomain(request);

        repository.save(comment);

        return "Comentário criado!";
    }

    //Método que retorna uma lista de comentários de relacionados ao ID de Post passado no parâmetro
    public List<CommentResponse> getPostComments(Integer postId) {
        List<Comment> commentList = repository.findAllByPostId(postId);

        return commentList.stream().map(comment -> {
                    User user = userService.findById(comment.getUserId());
                    UserCommentResponse userCommentResponse = userMapper.toCommentResponse(user);

                    return mapper.toResponse(comment, userCommentResponse);
                })
                .collect(Collectors.toList());
    }
}
