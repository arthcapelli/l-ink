package br.com.tcc.link.service.post;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.domain.PostTag;
import br.com.tcc.link.domain.User;
import br.com.tcc.link.mapper.PostMapper;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.PostRepository;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.post.PostPageResponse;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.comment.CommentService;
import br.com.tcc.link.service.favorite.FavoriteService;
import br.com.tcc.link.service.tag.PostTagService;
import br.com.tcc.link.service.tag.UserTagService;
import br.com.tcc.link.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CommentService commentService;

    //Método que realiza a adição do Post no banco de dados e das suas respectivas Tags
    public String create(final CreatePostRequest request) {
        Post post = mapper.toDomain(request);

        repository.save(post);

        request.getPostTags().forEach(tag -> postTagService.save(tag, post.getId()));

        return "Post criado!";
    }

    //Método para deleção do Post e de suas Tags, tendo como referência o seu postId
    public void delete(final Integer postId) {
        Post post = repository.findPostById(postId);
        postTagService.deleteAllByPostId(postId);
        repository.delete(post);
    }

    //Método que retorna a lista de todos os posts salvos no banco de dados, associados às suas respectivas Tags
    public List<PostResponse> getAllPosts(final Integer authUserId, final List<String> filterTags) {
        List<Post> postList = getFilteredPosts(filterTags);

        return postList.stream()
                .map(post -> {
                    UserResponse userResponse = getUserResponse(post);
                    Boolean isFavorite = favoriteService.existsFavorite(authUserId, post.getId());

                    return mapper.toPostResponse(post,
                            postTagService.findAllByPostId(post.getId()), userResponse, isFavorite);
                })
                .collect(Collectors.toList());
    }

    private List<Post> getFilteredPosts(List<String> filterTags) {
        List<Post> postList;
        if (!isNull(filterTags)) {
            List<Integer> postIdFiltered = new ArrayList<>();
            List<PostTag> postTags = postTagService.findAllByTagNameIn(filterTags);
            List<Integer> postIds = postTags.stream().map(PostTag::getPostId).collect(Collectors.toList());

            for (int i = 0; i < postIds.size(); i++) {
                int cont = 0;
                for (Integer postId : postIds) {
                    if (Objects.equals(postIds.get(i), postId)) {
                        cont++;
                    }
                }
                if (cont == filterTags.size() && !postIdFiltered.contains(postIds.get(i))) {
                    postIdFiltered.add(postIds.get(i));
                }
            }
            postList = repository.findAllByIdIn(postIdFiltered);
            Collections.reverse(postList);
        } else {
            postList = repository.findAllByOrderByIdDesc();
        }
        return postList;
    }

    //Método que recebe um postId como parâmetro e verifica se o mesmo existe no banco de dados
    public boolean existsById(Integer postId) {
        return repository.existsById(postId);
    }

    //Método que recebe um postId e authUserId como parametro e retorna um PostPageResponse para ser utilizado na construção
    //da página do Post no front
    public PostPageResponse getPostById(final Integer postId, final Integer authUserId) {
        Post postDomain = repository.findPostById(postId);

        UserResponse userResponse = getUserResponse(postDomain);

        List<CommentResponse> comments = commentService.getPostComments(postDomain.getId());
        Boolean isFavorite = favoriteService.existsFavorite(authUserId, postDomain.getId());
        List<String> postTag = postTagService.findAllByPostId(postDomain.getId());

        return mapper.toPostPageResponse(postDomain, postTag, userResponse, isFavorite, comments);
    }

    //Método que recebe um userId e authUserId como parametro e retorna a lista de PostResponse com base no userId para
    // ser utilizado na construção da página de perfil do usuário no front
    public List<PostResponse> getPostsByUserId(final Integer userId, final Integer authUserId) {
        return repository.findAllByUserIdOrderByIdDesc(userId).stream().map(post -> {
            UserResponse userResponse = getUserResponse(post);
            Boolean isFavorite = favoriteService.existsFavorite(authUserId, post.getId());
            List<String> postTags = postTagService.findAllByPostId(post.getId());

            return mapper.toPostResponse(post, postTags, userResponse, isFavorite);
        }).collect(Collectors.toList());
    }

    private UserResponse getUserResponse(Post postDomain) {
        User user = userService.findById(postDomain.getUserId());
        List<String> userTags = userTagService.findAllByUserId(postDomain.getUserId());
        return userMapper.toUserResponse(user, userTags);
    }
}
