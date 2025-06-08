package com.personal.securenotesapp.controller;

import com.personal.securenotesapp.dto.NoteDTO;
import com.personal.securenotesapp.dto.NoteMapper;
import com.personal.securenotesapp.model.Note;
import com.personal.securenotesapp.model.User;
import com.personal.securenotesapp.model.UserPrincipal;
import com.personal.securenotesapp.service.NoteService;
import com.personal.securenotesapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<?> getNotes(@AuthenticationPrincipal UserPrincipal userDetails) {
        List<Note> allNotes = noteService.getAllNotesByEmail(userDetails.getUsername());
        List<NoteDTO> dtoList = allNotes.stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<?> getNote(@AuthenticationPrincipal UserPrincipal userDetails, @PathVariable int id) {
        Note note = noteService.getNoteById(id, userDetails);
        NoteDTO dto = NoteMapper.toDTO(note);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<?> saveNote(@RequestBody Note note, @AuthenticationPrincipal UserPrincipal userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());

        note.setUser(user);
        noteService.saveNote(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<?> updateNote(@RequestBody Note note, @AuthenticationPrincipal UserPrincipal userDetails, @PathVariable int id) {
        Note n = noteService.getNoteById(id, userDetails);

        if(n != null) {
            n.setContent(note.getContent());
            noteService.saveNote(n);
            return new ResponseEntity<>("Note updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable int id, @AuthenticationPrincipal UserPrincipal userDetails) {
        Note note = noteService.getNoteById(id, userDetails);

        if(note != null) {
            noteService.deleteNote(id);
            return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Deletion failed", HttpStatus.BAD_REQUEST);
    }
}
