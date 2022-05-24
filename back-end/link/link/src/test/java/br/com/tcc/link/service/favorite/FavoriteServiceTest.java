package br.com.tcc.link.service.favorite;

import br.com.tcc.link.domain.Favorite;
import br.com.tcc.link.mapper.FavoriteMapper;
import br.com.tcc.link.repository.FavoriteRepository;
import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;
import br.com.tcc.link.service.post.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.tcc.link.fixture.CreateFavoriteRequestFixture.makeRandomCreateFavoriteRequest;
import static br.com.tcc.link.fixture.FavoriteFixture.makeRandomFavorite;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class FavoriteServiceTest {

    @InjectMocks
    private FavoriteService service;

    @Mock
    private FavoriteMapper mapper;

    @Mock
    private FavoriteRepository repository;

    @Test
    public void favoritePostWithSuccess() {
        CreateFavoriteRequest request = makeRandomCreateFavoriteRequest();
        Favorite favorite = makeRandomFavorite(request.getPostId(), request.getUserId());

        when(repository.existsByUserIdAndPostId(request.getUserId(), request.getPostId())).thenReturn(false);
        when(mapper.toDomain(request)).thenReturn(favorite);

        service.favoritePost(request);

        verify(repository).save(favorite);
    }

    @Test
    public void removeFavoritePostWithSuccess() {
        CreateFavoriteRequest request = makeRandomCreateFavoriteRequest();
        Favorite favorite = makeRandomFavorite(request.getPostId(), request.getUserId());

        when(repository.existsByUserIdAndPostId(request.getUserId(), request.getPostId())).thenReturn(true);

        service.favoritePost(request);

        verify(repository).deleteByUserIdAndPostId(favorite.getUserId(), favorite.getPostId());
    }

}