package uz.ilm.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uz.ilm.security.service.UserDetailsServiceCustom;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    public static final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    private final UserDetailsServiceCustom userDetailsServiceCustom;

    public WebSecurityConfig(UserDetailsServiceCustom userDetailsServiceCustom) {
        this.userDetailsServiceCustom = userDetailsServiceCustom;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll().and().httpBasic();

//        http
//                .authorizeRequests()
//                .antMatchers("/users/*")
//                .hasAnyAuthority()
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
//                .antMatchers("/users").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .defaultSuccessUrl("/auth/success")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .invalidateHttpSession(false)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Basic
    protected AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceCustom);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles(Roles.USER.name())
//                        .build();
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("password")
//                        .roles(Roles.ADMIN.name())
//                        .build();
//
//        inMemoryUserDetailsManager.createUser(user);
//        inMemoryUserDetailsManager.createUser(admin);
//        return inMemoryUserDetailsManager;
//    }
}
