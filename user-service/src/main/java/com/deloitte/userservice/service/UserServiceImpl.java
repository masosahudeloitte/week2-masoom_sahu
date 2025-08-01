package com.deloitte.userservice.service;

import com.deloitte.userservice.exception.UserNotFoundException;
import com.deloitte.userservice.model.User;
import com.deloitte.userservice.model.dto.IssueDto;
import com.deloitte.userservice.repository.UserRepository;
import com.deloitte.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password."));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getUsersByRole(int role) {
        return userRepository.findByRole(role);
    }
    
    @Override
    public List<IssueDto> getIssuesByUserId(int userId) {
        String url = "http://localhost:8083/api/issues/assignee/" + userId;
        ResponseEntity<IssueDto[]> response = restTemplate.getForEntity(url, IssueDto[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            return new ArrayList<>();
        }
    }
}
