package br.com.tcc.link.web.authentication;

import br.com.tcc.link.representation.request.authentication.LoginRequest;
import br.com.tcc.link.representation.response.user.UserResponse;
import br.com.tcc.link.service.authentication.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody final LoginRequest request) {
        return loginService.login(request);
    }
}
