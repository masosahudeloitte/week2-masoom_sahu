package com.deloitte.userservice.service;

import com.deloitte.userservice.model.User;
import com.deloitte.userservice.model.dto.IssueDto;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    List<User> getAllUsers();
    User getUserById(int userId);
    List<User> getUsersByRole(int role);
    List<IssueDto> getIssuesByUserId(int userId);
}
