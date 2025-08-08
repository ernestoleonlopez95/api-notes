package com.app.otten.notes.util;

public final class NoteConstants {

    private NoteConstants(){}

    public static final class ApiPath {
        public static final String BASE = "/api/v1/note";
        public static final String FIND = "/find";
        public static final String SAVE = "/save";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class ResponseKeys {
        public static final String STATUS = "status";
        public static final String MESSAGE = "message";
        public static final String DATA = "data";
        public static final String ERRORS = "errors";
    }

    public static final class TypesDate {
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
    }

}
