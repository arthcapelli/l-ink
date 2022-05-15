package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.Post;
import br.com.tcc.link.representation.request.post.CreatePostRequest;
import br.com.tcc.link.representation.response.post.PostResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    public Post toDomain(final CreatePostRequest request) {
      return Post.builder()
              .postImg(request.getPostImg())
              .bodyLocal(request.getBodyLocal())
              .measures(request.getMeasures())
              .userId(request.getUserId())
              .build();
    }

    public PostResponse toPostResponse(final Post post, final List<String> postTags, final UserResponse userResponse){
        return PostResponse.builder()
                .id(post.getId())
                .postImg(post.getPostImg())
                .bodyLocal(post.getBodyLocal())
                .measures(post.getMeasures())
                .userResponse(userResponse)
                .postTags(postTags)
                .build();
    }
}
