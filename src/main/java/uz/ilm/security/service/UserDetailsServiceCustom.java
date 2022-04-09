package uz.ilm.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ilm.security.model.UserInfo;
import uz.ilm.security.model.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsServiceIml")
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
