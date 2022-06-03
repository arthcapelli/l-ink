package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.comment.CommentResponse;
import br.com.tcc.link.representation.response.post.PostPageResponse;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostMapper {

    //Método que realiza conversão de CreatePostRequest para Post, para que o mesmo seja salvo no banco de dados
    public Post toDomain(final CreatePostRequest request) {
        return Post.builder()
                .postImg(request.getPostImg())
                .bodyLocal(request.getBodyLocal())
                .measures(request.getMeasures())
                .userId(request.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //Método que realiza conversão de Post para PostResponse, para que o mesmo seja utilizado no front
    public PostResponse toPostResponse(final Post post, final List<String> postTags, final UserResponse userResponse, final Boolean isFavorite) {
        return PostResponse.builder()
                .id(post.getId())
                .postImg(post.getPostImg())
                .bodyLocal(post.getBodyLocal())
                .measures(post.getMeasures())
                .userResponse(userResponse)
                .postTags(postTags)
                .isFavorite(isFavorite)
                .createdAt(post.getCreatedAt().toString())
                .build();
    }

    //Método que realiza conversão de Post para PostPageResponse, para que o mesmo seja utilizado na sua página no front
    public PostPageResponse toPostPageResponse(final Post post,
                                               final List<String> postTags,
                                               final UserResponse userResponse,
                                               final Boolean isFavorite,
                                               final List<CommentResponse> commentResponse) {
        return PostPageResponse.builder()
                .id(post.getId())
                .postImg(post.getPostImg())
                .bodyLocal(post.getBodyLocal())
                .measures(post.getMeasures())
                .userResponse(userResponse)
                .postTags(postTags)
                .isFavorite(isFavorite)
                .comments(commentResponse)
                .createdAt(post.getCreatedAt().toString())
                .build();
    }
}
