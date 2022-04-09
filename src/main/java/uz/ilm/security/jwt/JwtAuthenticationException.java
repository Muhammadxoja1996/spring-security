package uz.ilm.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * Author: Muhammadxo'ja
 * Date: 06.04.2022
 * Time: 22:33
 */
@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg,HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}