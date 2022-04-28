package br.com.tcc.link.web.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacao")
public class AuthenticationController {


    /*
        @Autowired
        LogarUsuarioService logarUsuarioService;
    */


    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String logarUsuario() {
        return null;
        //       return logarUsuarioService.logarUsuario(request);
    }
}
