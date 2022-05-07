package br.com.tcc.link.representation.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String postImg;
    private String bodyLocal;
    private String measures;
    private Integer userId;
    private List<String> postTags;
}
