package com.app.otten.notes.exception;

import com.app.otten.notes.util.MessageHelper;
import com.app.otten.notes.util.NoteConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageHelper messageHelper;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException be) {
        logger.warn("Error de negocio: {}", be.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put(NoteConstants.ResponseKeys.STATUS, false);
        response.put(NoteConstants.ResponseKeys.MESSAGE, be.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        logger.error("Ocurrio un error inesperado: ", ex);
        Map<String, Object> response = new HashMap<>();
        response.put(NoteConstants.ResponseKeys.STATUS, false);
        response.put(NoteConstants.ResponseKeys.MESSAGE, messageHelper.getMessage("note.InternalError") + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
