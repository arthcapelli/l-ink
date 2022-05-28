package br.com.tcc.link.representation.response.comment;

import br.com.tcc.link.representation.response.user.UserCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private int id;
    private String commentText;
    private UserCommentResponse userResponse;
}
