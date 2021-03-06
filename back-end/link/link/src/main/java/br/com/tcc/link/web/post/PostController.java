package br.com.tcc.link.web.post;

import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.post.PostPageResponse;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody final CreatePostRequest request) {
        return postService.create(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Integer postId) {
        postService.delete(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(@RequestParam Integer authUserId, @RequestParam(required = false) List<String> filterTags) {
        return postService.getAllPosts(authUserId, filterTags);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostPageResponse getPost(@PathVariable Integer postId, @RequestParam Integer authUserId) {
        return postService.getPostById(postId, authUserId);
    }

    @GetMapping("user-posts/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsByUserId(@PathVariable Integer userId, @RequestParam Integer authUserId) {
        return postService.getPostsByUserId(userId, authUserId);
    }
}
