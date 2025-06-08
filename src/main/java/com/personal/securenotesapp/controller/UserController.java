package com.personal.securenotesapp.controller;

import com.personal.securenotesapp.model.Note;
import com.personal.securenotesapp.model.User;
import com.personal.securenotesapp.service.NoteService;
import com.personal.securenotesapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestPart User user, @RequestPart MultipartFile imgFile) throws IOException {
        userService.saveUser(user, imgFile);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/notes")
    public ResponseEntity<?> getAllNotes() {
        List<Note> allNotes = noteService.getAllNotes();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user != null)
            return ResponseEntity.ok()
                    .header("Content-Type", user.getImageType())
                    .body(user.getImageData());
        else
            return new ResponseEntity<>("Unable to get the user", HttpStatus.BAD_REQUEST);
    }
}
