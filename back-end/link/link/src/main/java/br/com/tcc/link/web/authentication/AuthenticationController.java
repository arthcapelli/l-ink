package br.com.tcc.link.web.authentication;

import br.com.tcc.link.representation.request.authentication.CreateUserRequest;
import br.com.tcc.link.service.authentication.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacao")
public class AuthenticationController {

    @Autowired
    CreateUserService createUserService;
    /*
        @Autowired
        LogarUsuarioService logarUsuarioService;
    */
    @PostMapping("criar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody final CreateUserRequest request) {
       createUserService.create(request);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String logarUsuario() {
        return null;
        //       return logarUsuarioService.logarUsuario(request);
    }
}
