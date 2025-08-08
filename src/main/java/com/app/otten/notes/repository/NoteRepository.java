package com.app.otten.notes.repository;

import com.app.otten.notes.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}

