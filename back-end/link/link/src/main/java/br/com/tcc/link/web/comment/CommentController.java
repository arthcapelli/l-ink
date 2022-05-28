package br.com.tcc.link.web.comment;

import br.com.tcc.link.representation.request.comment.CreateCommentRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("criar-comentario")
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestBody final CreateCommentRequest request) {
        return service.createComment(request);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getPostComments(@PathVariable Integer postId) {
        return service.getPostComments(postId);
    }
}
