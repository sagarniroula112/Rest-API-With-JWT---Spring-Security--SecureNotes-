package com.personal.securenotesapp.service;

import com.personal.securenotesapp.model.User;
import com.personal.securenotesapp.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void saveUser(User user, MultipartFile img) throws IOException {
        user.setImageName(img.getOriginalFilename());
        user.setImageType(img.getContentType());
        user.setImageData(img.getBytes());

        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id).orElse(new User());
    }
}
