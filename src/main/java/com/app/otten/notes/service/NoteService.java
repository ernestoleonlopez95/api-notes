package com.app.otten.notes.service;

import com.app.otten.notes.dto.NoteDTO;
import com.app.otten.notes.entity.Note;
import com.app.otten.notes.exception.BusinessException;
import com.app.otten.notes.repository.NoteRepository;
import com.app.otten.notes.service.mapper.INoteMapper;
import com.app.otten.notes.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService implements INoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private INoteMapper noteMapper;
    @Autowired
    private MessageHelper messageHelper;

    @Override
    public List<NoteDTO> findAllNotes() {
        List<Note> noteList = (List<Note>) noteRepository.findAll();
        List<NoteDTO> noteDTOList = new ArrayList<>();
        if(!noteList.isEmpty())
            noteDTOList = noteMapper.toDtoList(noteList);
        return noteDTOList;
    }

    @Override
    public List<NoteDTO> findNotesBetweenDates(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return noteMapper.toDtoList(noteRepository.findByCreatedAtBetween(startDateTime, endDateTime));
    }

    @Override
    public void saveNote(NoteDTO noteDTO) {
        LocalDateTime now = LocalDateTime.now();
        noteDTO.setCreatedAt(now);
        noteDTO.setUpdatedAt(now);
        noteDTO.setStatus(Boolean.TRUE);
        Note newNote = noteRepository.save(noteMapper.toEntity(noteDTO));
        if(newNote.getId() == null)
            throw new BusinessException(messageHelper.getMessage("note.notSaved"));
    }

    @Override
    public void updateNote(NoteDTO noteDTO) {

        if(!noteRepository.existsById(noteDTO.getId()))
            throw new BusinessException(messageHelper.getMessage("note.notFound"));

        if(noteDTO.getStatus() == null)
            throw new BusinessException(messageHelper.getMessage("note.valData.status.notnull"));

        Note note = noteRepository.findById(noteDTO.getId()).get();
        noteDTO.setCreatedAt(note.getCreatedAt());
        noteDTO.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(noteMapper.toEntity(noteDTO));
    }

    @Override
    public void deleteNote(Long idNote) {
        if(!noteRepository.existsById(idNote))
            throw new BusinessException(messageHelper.getMessage("note.notFound"));
        noteRepository.deleteById(idNote);
    }

}
