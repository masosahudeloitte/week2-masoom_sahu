package com.deloitte.userservice.controller;

import com.deloitte.userservice.model.User;
import com.deloitte.userservice.model.dto.IssueDto;
import com.deloitte.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("Your account is created successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedIn = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(loggedIn);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Integer role) {
        if (role == null || role != 1) {
            return ResponseEntity.status(403).body("Access Denied. Only project owners can view all users.");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable int role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }
    

@GetMapping("/id/{userId}/issues")
public ResponseEntity<List<IssueDto>> getIssuesByUserId(@PathVariable int userId) {
    List<IssueDto> issues = userService.getIssuesByUserId(userId);
    return new ResponseEntity<>(issues, HttpStatus.OK);
}
}
