package com.app.otten.notes.controller;

import com.app.otten.notes.dto.NoteDTO;
import com.app.otten.notes.service.INoteService;
import com.app.otten.notes.util.MessageHelper;
import com.app.otten.notes.util.NoteConstants;
import com.app.otten.notes.util.NotesUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/note")
public class NoteController {

    @Autowired
    private INoteService noteService;
    @Autowired
    private MessageHelper messageHelper;

    @GetMapping({"/find", "/find/"})
    public ResponseEntity<?> findAllNotes() {
        List<NoteDTO> noteDTOList = noteService.findAllNotes();
        if( noteDTOList != null && !noteDTOList.isEmpty() ) {
            return ResponseEntity.ok(noteDTOList);
        } else {
            return  ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/find/bwdate/{startDate}/{endDate}")
    public ResponseEntity<?> findNotesBetweenDates(
            @PathVariable @DateTimeFormat(pattern = NoteConstants.TypesDate.YYYY_MM_DD) LocalDate startDate,
            @PathVariable @DateTimeFormat(pattern = NoteConstants.TypesDate.YYYY_MM_DD) LocalDate endDate) {

        List<NoteDTO> notesNoteDTOS = noteService.findNotesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(notesNoteDTOS);
    }

    @PostMapping({"/save", "/save/"})
    public ResponseEntity<?> saveNote(@Valid @RequestBody NoteDTO noteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NotesUtils.getValidError(bindingResult, messageHelper.getMessage("note.invalid"));
        }
        noteService.saveNote(noteDTO);
        return ResponseEntity.ok(Map.of(
                NoteConstants.ResponseKeys.STATUS, Boolean.TRUE,
                NoteConstants.ResponseKeys.MESSAGE, messageHelper.getMessage("note.saved")
        ));
    }

    @PutMapping({"/update", "/update/"})
    public ResponseEntity<?> updateNote(@Valid @RequestBody NoteDTO noteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NotesUtils.getValidError(bindingResult, messageHelper.getMessage("note.invalid"));
        }
        noteService.updateNote(noteDTO);
        return ResponseEntity.ok(Map.of(
                NoteConstants.ResponseKeys.STATUS, Boolean.TRUE,
                NoteConstants.ResponseKeys.MESSAGE, messageHelper.getMessage("note.noteUpdated")
        ));
    }

    @DeleteMapping("/delete/{idNote}")
    public ResponseEntity<?> deleteNote(@PathVariable Long idNote) {
        Map<String, Object> response = new HashMap<>();
        noteService.deleteNote(idNote);
        response.put(NoteConstants.ResponseKeys.STATUS, Boolean.TRUE);
        response.put(NoteConstants.ResponseKeys.MESSAGE, messageHelper.getMessage("note.deleted"));
        return ResponseEntity.ok(response);
    }

}
