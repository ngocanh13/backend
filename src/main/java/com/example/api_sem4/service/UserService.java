package com.example.api_sem4.service;

import com.example.api_sem4.dto.LoginUser;
import com.example.api_sem4.dto.RegisterUser;
import com.example.api_sem4.entity.User;
import com.example.api_sem4.entity.Permission;
import com.example.api_sem4.repository.UserRepository;
import com.example.api_sem4.repository.PermissionRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Constructor cần thêm PermissionRepository vào
    public UserService(UserRepository userRepository, PermissionRepository permissionRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegisterUser input){
        if (userExists(input.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setPhone(input.getPhone());  // Gán phone từ DTO vào entity

        Permission permission = assignRoleToPermission(input.getRole());

        user.getPermissions().add(permission);  // Gán quyền cho người dùng
        return userRepository.save(user);  // Lưu người dùng vào DB
    }

    private Permission assignRoleToPermission(String role) {
        Permission permission;
        switch (role.toLowerCase()) {
            case "admin":
                permission = permissionRepository.findByPermission("ROLE_ADMIN");
                if (permission == null) {
                    permission = new Permission();
                    permission.setPermission("ROLE_ADMIN");
                    permission.setName("Admin Role");
                    permission = permissionRepository.save(permission);
                }
                break;
            case "teacher":
                permission = permissionRepository.findByPermission("ROLE_TEACHER");
                if (permission == null) {
                    permission = new Permission();
                    permission.setPermission("ROLE_TEACHER");
                    permission.setName("Teacher Role");
                    permission = permissionRepository.save(permission);
                }
                break;
            case "parent":
                permission = permissionRepository.findByPermission("ROLE_PARENT");
                if (permission == null) {
                    permission = new Permission();
                    permission.setPermission("ROLE_PARENT");
                    permission.setName("Parent Role");
                    permission = permissionRepository.save(permission);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
        return permission;
    }

    public User authenticate(LoginUser input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(), input.getPassword()
                )
        );
        User user = userRepository.findByEmail(input.getEmail());
        if(user == null) throw new UsernameNotFoundException("Email or password is not found");
        return user;
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
