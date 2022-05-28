package br.com.tcc.link.representation.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentResponse {

    private Integer id;
    private String name;
    private String avatar;
}
