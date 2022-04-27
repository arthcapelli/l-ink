package br.com.tcc.link.representation.request.authentication;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String avatar;
    private Boolean isTattooArtist;
    private List<String> interests;
}
