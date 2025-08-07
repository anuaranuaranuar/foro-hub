package com.anuar.foro_hub.exception;

public class TopicAlreadyExistException extends RuntimeException{

    public TopicAlreadyExistException(String message){
        super(message);
    }
    
}