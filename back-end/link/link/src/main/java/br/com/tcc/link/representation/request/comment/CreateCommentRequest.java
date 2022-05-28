package br.com.tcc.link.representation.request.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

    private int userId;
    private int postId;
    private String commentText;
}
