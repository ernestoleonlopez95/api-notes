package com.app.otten.notes.service.mapper;

import com.app.otten.notes.dto.NoteDTO;
import com.app.otten.notes.entity.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface INoteMapper {

    NoteDTO toDto(Note note);

    List<NoteDTO> toDtoList(List<Note> notes);

    Note toEntity(NoteDTO dto);

}
