package com.personal.securenotesapp.dto;

import com.personal.securenotesapp.model.Note;

public class NoteMapper {

    public static NoteDTO toDTO(Note note) {
        return new NoteDTO(note.getId(), note.getContent());
    }

    public static Note toEntity(NoteDTO dto) {
        Note note = new Note();
        note.setId(dto.getId());
        note.setContent(dto.getContent());
        return note;
    }
}

