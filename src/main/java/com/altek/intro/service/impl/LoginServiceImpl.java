package com.altek.intro.service.impl;

import com.altek.intro.dto.request.LoginRequestDto;
import com.altek.intro.dto.response.JwtResponse;
import com.altek.intro.entity.User;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.repository.UserRepository;
import com.altek.intro.security.jwt.JwtUtils;
import com.altek.intro.service.LoginService;
import com.altek.intro.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public JwtResponse login(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Optional<User> optional = userRepository.findByUsername(loginRequest.getUsername());
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("account.not.found.with.username:%s",loginRequest.getUsername()));
        }
        User user = optional.get();
        if (user.getStatus().equals(Constant.DELETE)) {
            throw new ResourceNotFoundException("account.not.active");
        }
        return new JwtResponse(jwt, "Bearer", user.getId(), roles, user.getUsername());
    }
}
