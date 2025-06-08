package com.personal.securenotesapp.repo;

import com.personal.securenotesapp.model.Note;
import com.personal.securenotesapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Integer> {
    List<Note> findAllByUser(User user);
}
