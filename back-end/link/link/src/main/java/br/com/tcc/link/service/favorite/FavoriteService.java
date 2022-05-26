package br.com.tcc.link.service.favorite;

import br.com.tcc.link.domain.Favorite;
import br.com.tcc.link.mapper.FavoriteMapper;
import br.com.tcc.link.repository.FavoriteRepository;
import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repository;

    @Autowired
    private FavoriteMapper mapper;

    //Método que recebe CreateFavoriteRequest como parâmetro e verifica se o post relacionado já está favoritado.
    //Se sim, ele remove o Favorite, se não ele cria o mesmo e salva no banco
    @Transactional
    public void favoritePost(CreateFavoriteRequest request) {
        if (existsFavorite(request.getUserId(), request.getPostId())) {
            removeFavorite(request);
        } else {
            Favorite favorite = mapper.toDomain(request);
            repository.save(favorite);
        }
    }

    //Método que verifica se o usuário favoritou um post específico.
    public boolean existsFavorite(Integer userId, Integer postId) {
        return repository.existsByUserIdAndPostId(userId, postId);
    }

    //Método que realiza a remoção do Favorite do banco de dados
    private void removeFavorite(CreateFavoriteRequest request) {
        repository.deleteByUserIdAndPostId(request.getUserId(), request.getPostId());
    }
}
