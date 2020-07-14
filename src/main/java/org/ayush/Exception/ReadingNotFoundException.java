package org.ayush.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReadingNotFoundException extends RuntimeException {

    public ReadingNotFoundException(String message) {
        super(message);
    }
}
