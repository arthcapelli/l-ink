package br.com.tcc.link.representation.response.post;

import br.com.tcc.link.representation.response.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Integer id;
    private String postImg;
    private String bodyLocal;
    private String measures;
    private UserResponse userResponse;
    private List<String> postTags;
}
