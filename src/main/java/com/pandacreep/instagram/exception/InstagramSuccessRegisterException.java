package com.pandacreep.instagram.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstagramSuccessRegisterException extends InstagramException{
    public String message;

    public InstagramSuccessRegisterException() {
        super();
    }

    public InstagramSuccessRegisterException(String message) {
        super(message);
        this.message = message;
    }
}
