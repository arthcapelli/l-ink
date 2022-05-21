package br.com.tcc.link.fixture;

import br.com.tcc.link.domain.Favorite;

public class FavoriteFixture {
    public static Favorite makeRandomFavorite(final Integer postId, final Integer userId) {
        return Favorite.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
