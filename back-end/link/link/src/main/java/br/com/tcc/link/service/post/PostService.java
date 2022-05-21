package br.com.tcc.link.service.post;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.domain.User;
import br.com.tcc.link.mapper.PostMapper;
import br.com.tcc.link.mapper.UserMapper;
import br.com.tcc.link.repository.PostRepository;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.tag.PostTagService;
import br.com.tcc.link.service.tag.UserTagService;
import br.com.tcc.link.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PostResponse> getAllPosts() {
        List<Post> postList = (List<Post>) repository.findAll();

        return postList.stream()
                .map(post -> {
                    User user = userService.findById(post.getUserId());
                    List<String> userTags = userTagService.findAllByUserId(post.getUserId());
                    UserResponse userResponse = userMapper.toUserResponse(user, userTags);

                    return mapper.toPostResponse(post, postTagService.findAllByPostId(post.getId()), userResponse);
                })
                .collect(Collectors.toList());
    }

    //Método que recebe um postId como parâmetro e verifica se o mesmo existe no banco de dados
    public boolean existsById(Integer postId) {
        return repository.existsById(postId);
    }
}
