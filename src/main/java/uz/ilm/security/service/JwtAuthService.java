package uz.ilm.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uz.ilm.security.jwt.JwtTokenProvider;
import uz.ilm.security.model.AuthUser;
import uz.ilm.security.model.UserInfo;
import uz.ilm.security.model.UserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Muhammadxo'ja
 * Date: 06.04.2022
 * Time: 22:14
 */
@Service
public class JwtAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public Map<String, String> login(AuthUser authUser) {
        try {
            UserInfo userInfo = userRepository.findByUserName(authUser.getLogin()).get();
            String token = jwtTokenProvider.createToken(userInfo.getUserName(), userInfo.getRoles().name());
            Map<String, String> map = new HashMap<>();
            map.put("username", userInfo.getUserName());
            map.put("token", token);
            return map;
        } catch (Throwable e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            return map;
        }
    }
}
