package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Favorite;
import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    //Método que realiza conversão de CreateFavoriteRequest para Favorite, para que o mesmo seja salvo no banco de dados
    public Favorite toDomain(final CreateFavoriteRequest request){
        return Favorite.builder()
                .postId(request.getPostId())
                .userId(request.getUserId())
                .build();
    }
}
