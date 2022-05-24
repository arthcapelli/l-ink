package br.com.tcc.link.web.favorite;

import br.com.tcc.link.representation.request.favorite.CreateFavoriteRequest;
import br.com.tcc.link.service.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService service;

    @PostMapping("favoritar-post")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody final CreateFavoriteRequest request) {
        service.favoritePost(request);
    }
}
