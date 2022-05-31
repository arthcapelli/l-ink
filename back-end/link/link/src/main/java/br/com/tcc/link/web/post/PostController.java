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

    @PostMapping("criar-post")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody final CreatePostRequest request) {
        return postService.create(request);
    }

    @DeleteMapping("deletar-post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Integer postId) {
        postService.delete(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(@RequestParam Integer authUserId) {
        return postService.getAllPosts(authUserId);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostPageResponse getPost(@RequestParam Integer authUserId, @PathVariable Integer postId) {
        return postService.getPostById(postId, authUserId);
    }
}
