package br.com.tcc.link.representation.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String name;
    private String avatar;
    private Integer expTime;
    private boolean isTattooArtist;
    private List<String> userTags;
    private String location;
    private String phone;
}
