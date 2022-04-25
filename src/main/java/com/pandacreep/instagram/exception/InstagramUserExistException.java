package com.pandacreep.instagram.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstagramUserExistException extends InstagramException{
    private String message;

    public InstagramUserExistException() {
        super();
    }

    public InstagramUserExistException(String message) {
        super(message);
        this.message = message;
    }
}
