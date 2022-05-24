package br.com.tcc.link.representation.request.favorite;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteRequest {

    private Integer postId;

    private Integer userId;
}
