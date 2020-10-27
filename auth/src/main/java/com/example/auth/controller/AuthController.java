package com.example.auth.controller;

import com.example.auth.dto.UserCredentials;
import com.example.auth.entity.AppUser;
import com.example.auth.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<AppUser>> findAll(){
        return new ResponseEntity<>(userMapper.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(userMapper.findByUsername(username),HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> insertUser(@RequestBody AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return new ResponseEntity<>("Insert user successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserCredentials userCredentials){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()))
    }
}
