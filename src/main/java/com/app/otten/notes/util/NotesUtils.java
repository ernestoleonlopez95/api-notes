package com.app.otten.notes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public abstract class NotesUtils {

    public static String getDateNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static ResponseEntity<?> getValidError(BindingResult bindingResult, String errorMessage) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(errorItem ->
                errors.put(errorItem.getField(), errorItem.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(Map.of(
                NoteConstants.ResponseKeys.STATUS, Boolean.FALSE,
                NoteConstants.ResponseKeys.MESSAGE, errorMessage,
                NoteConstants.ResponseKeys.ERRORS, errors
        ));
    }

}
