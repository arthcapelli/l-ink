package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Comment;
import br.com.tcc.link.representation.request.comment.CreateCommentRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.user.UserCommentResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    //Método que realiza a conversão de CreateCommentRequest para Comment
    public Comment toDomain(CreateCommentRequest request) {
        return Comment.builder()
                .commentText(request.getCommentText())
                .postId(request.getPostId())
                .userId(request.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //Método que recebe como parâmetros o Comment e UserCommentResponse para construir e retornar um CommentResponse
    public CommentResponse toResponse(Comment comment, UserCommentResponse userResponse) {
        return CommentResponse.builder()
                .id(comment.getId())
                .commentText(comment.getCommentText())
                .userResponse(userResponse)
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}
