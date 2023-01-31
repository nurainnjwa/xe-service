package com.xeTraining.wizardInfoservice.exception.server;

public class ServerErrorException extends RuntimeException{
    public ServerErrorException() {
    }

    public ServerErrorException(String message) {
        super(message);
    }

}
