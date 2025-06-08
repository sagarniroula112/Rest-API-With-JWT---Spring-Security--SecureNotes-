package com.personal.securenotesapp.service;

import com.personal.securenotesapp.model.Note;
import com.personal.securenotesapp.model.User;
import com.personal.securenotesapp.repo.NoteRepo;
import com.personal.securenotesapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Note> getAllNotesByEmail(String username) {
        User user = userRepo.findByEmail(username);
        return noteRepo.findAllByUser(user);
    }

    public Note getNoteById(int id, UserDetails userDetails) {
        Optional<Note> note = noteRepo.findById(id);
        if(note.get().getUser().getEmail().equals(userDetails.getUsername())) {
            return note.get();
        } else
            return null;
    }

    public void saveNote(Note note) {
        noteRepo.save(note);
    }

    public void deleteNote(int id) {
        noteRepo.deleteById(id);
    }

    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }
}
