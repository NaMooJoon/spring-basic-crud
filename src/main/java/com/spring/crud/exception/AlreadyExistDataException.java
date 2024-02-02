package com.spring.crud.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@NoArgsConstructor
public class AlreadyExistDataException extends RuntimeException {
    public AlreadyExistDataException(String msg) {
        super(msg);
    }
}
