package br.com.tcc.link.web.comment;

import br.com.tcc.link.representation.request.comment.CreateCommentRequest;
import br.com.tcc.link.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestBody final CreateCommentRequest request) {
        return service.createComment(request);
    }
}
