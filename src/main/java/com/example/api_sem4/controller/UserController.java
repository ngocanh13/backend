package com.example.api_sem4.controller;

import com.example.api_sem4.dto.LoginResponse;
import com.example.api_sem4.dto.LoginUser;
import com.example.api_sem4.dto.RegisterUser;
import com.example.api_sem4.entity.User;
import com.example.api_sem4.service.JwtService;
import com.example.api_sem4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser req){
        try {
            System.out.println("Register request: " + req);  // Log thông tin yêu cầu
            if (userService.userExists(req.getEmail())) {
                return ResponseEntity.badRequest().body("Email already in use.");
            }
            User user = userService.signUp(req);
            return ResponseEntity.status(201).body(user);
        } catch (Exception e) {
            e.printStackTrace();  // Log lỗi chi tiết
            return ResponseEntity.status(500).body("An error occurred while registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser req){
        try {
            User user = userService.authenticate(req);
            String jwtToken = jwtService.generateToken(user);
            LoginResponse rs = new LoginResponse();
            rs.setToken(jwtToken);

            return ResponseEntity.ok(rs); // Trả về HTTP 200 - OK
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials: " + e.getMessage());
        }
    }
}
