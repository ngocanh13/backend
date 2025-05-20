package com.example.api_sem4.controller;

import com.example.api_sem4.dto.LoginResponse;
import com.example.api_sem4.dto.LoginUser;
import com.example.api_sem4.dto.RegisterUser;
import com.example.api_sem4.entity.User;
import com.example.api_sem4.service.JwtService;
import com.example.api_sem4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        try {
            //sasass
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching users: " + e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser req) {
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
    public ResponseEntity<?> login(@RequestBody LoginUser req) {
        try {
            User user = userService.authenticate(req);

            List<String> roles = user.getPermissions().stream()
                    .map(p -> p.getPermission())
                    .collect(Collectors.toList());

            if (roles.isEmpty()) {
                return ResponseEntity.status(403).body("No permissions assigned to this user.");
            }

            String jwtToken = jwtService.generateToken(user);

            LoginResponse rs = new LoginResponse();
            rs.setToken(jwtToken);


            return ResponseEntity.ok(rs);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials: " + e.getMessage());
        }
    }
}
