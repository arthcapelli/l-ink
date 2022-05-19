package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
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
                .expTime(request.getExpTime())
                .build();
    }

    public UserResponse toUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .expTime(user.getExpTime())
                .isTattooArtist(user.getIsTattooArtist())
                .build();
    }
}
