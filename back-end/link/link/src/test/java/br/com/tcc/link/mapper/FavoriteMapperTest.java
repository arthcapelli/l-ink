package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Favorite;
import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.tcc.link.fixture.CreateFavoriteRequestFixture.makeRandomCreateFavoriteRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteMapperTest {
    private final FavoriteMapper mapper = new FavoriteMapper();

    @Test
    public void createFavoriteToDomain(){
        CreateFavoriteRequest request = makeRandomCreateFavoriteRequest();
        Favorite favorite = mapper.toDomain(request);

        assertEquals(request.getPostId(), favorite.getPostId());
        assertEquals(request.getUserId(), favorite.getUserId());

    }
}
