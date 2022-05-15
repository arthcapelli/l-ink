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
    private UserService userService;

    public void create(final CreatePostRequest request) {
        Post post = mapper.toDomain(request);

        repository.save(post);

        request.getPostTags().forEach(tag -> postTagService.save(tag, post.getId()));
    }

    public void delete(final Integer postId) {
        Post post = repository.findPostById(postId);
        postTagService.deleteAllByPostId(postId);
        repository.delete(post);
    }

    public List<PostResponse> getAllPosts() {
        List<Post> postList = (List<Post>) repository.findAll();

        return postList.stream()
                .map(post -> {
                    User user = userService.findById(post.getUserId());
                    UserResponse userResponse = userMapper.toUserResponse(user);

                    return mapper.toPostResponse(post, postTagService.findAllByPostId(post.getId()), userResponse);
                })
                .collect(Collectors.toList());
    }
}
