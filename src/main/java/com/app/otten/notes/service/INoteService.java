package com.app.otten.notes.service;

import com.app.otten.notes.dto.NoteDTO;

import java.time.LocalDate;
import java.util.List;

public interface INoteService {

    List<NoteDTO> findAllNotes();

    List<NoteDTO> findNotesBetweenDates(LocalDate startDate, LocalDate endDate);

    void saveNote(NoteDTO noteDTO);

    void updateNote(NoteDTO noteDTO);

    void deleteNote(Long idNote);
}
