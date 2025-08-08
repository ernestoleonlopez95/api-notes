package com.app.otten.notes.util;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper extends MessageSourceAccessor {

    public MessageHelper(MessageSource messageSource) {
        super(messageSource, Locale.getDefault());
    }

}
