package br.com.tcc.link.fixture;

import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class CreateFavoriteRequestFixture {
    public static CreateFavoriteRequest makeRandomCreateFavoriteRequest() {
        return CreateFavoriteRequest.builder()
                .userId(nextInt())
                .postId(nextInt())
                .build();
    }
}
