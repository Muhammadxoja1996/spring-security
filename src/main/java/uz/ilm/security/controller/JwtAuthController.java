package uz.ilm.security.controller;

import org.springframework.web.bind.annotation.*;
import uz.ilm.security.model.AuthUser;
import uz.ilm.security.service.JwtAuthService;

import java.util.Map;

/**
 * Author: Muhammadxo'ja
 * Date: 06.04.2022
 * Time: 22:12
 */
@RestController
@RequestMapping("/api/v1/auth")
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    public JwtAuthController(JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody AuthUser authUser) {
        return jwtAuthService.login(authUser);
    }


}
