package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(final CreateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .avatar(request.getAvatar())
                .isTattooArtist(request.getIsTattooArtist())
                .build();
    }
}
