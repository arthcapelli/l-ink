package br.com.tcc.link.mapper;

import br.com.tcc.link.domain.User;
import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.representation.response.user.UserCommentResponse;
import br.com.tcc.link.representation.response.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    //Método que realiza conversão de CreateUserRequest para User, para que o mesmo seja salvo no banco de dados
    public User toDomain(final CreateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .avatar(request.getAvatar())
                .isTattooArtist(request.getIsTattooArtist())
                .expTime(request.getExpTime())
                .location(request.getLocation())
                .phone(request.getPhone())
                .build();
    }

    //Método que realiza conversão de User para UserResponse, para que o mesmo seja utilizado no front
    public UserResponse toUserResponse(final User user, List<String> userTags) {
        String[] address = getAddress(user);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .expTime(user.getExpTime())
                .isTattooArtist(user.getIsTattooArtist())
                .userTags(userTags)
                .street(address[0])
                .city(address[1])
                .uf(address[2])
                .phone(user.getPhone())
                .build();
    }

    private String[] getAddress(User user) {
        String defaultLocation = " - - ";
        return user.getIsTattooArtist() ? user.getLocation().split("-") : defaultLocation.split("-");
    }

    //Método para converter User para UserCommentResponse, para que o mesmo seja utilizado no front
    public UserCommentResponse toCommentResponse(User user) {
        return UserCommentResponse.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .name(user.getName())
                .build();
    }
}
