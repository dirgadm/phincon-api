package com.phincon.api.service;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.phincon.api.entity.User;
import com.phincon.api.model.LoginUserRequest;
import com.phincon.api.model.TokenResponse;
import com.phincon.api.repository.UserRepository;
import com.phincon.api.security.BCrypt;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password mismatch"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder().
                token(user.getToken()).
                expiredAt(user.getTokenExpiredAt())
                .build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password mismatch");
        }
    }

    public Long next30Days(){
        return System.currentTimeMillis() + (1000* 60 * 24 *30);
    }
}
