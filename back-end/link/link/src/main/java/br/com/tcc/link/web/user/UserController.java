package br.com.tcc.link.web.user;


import br.com.tcc.link.representation.request.user.CreateUserRequest;
import br.com.tcc.link.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("criar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody final CreateUserRequest request) {
        userService.create(request);
    }
}
