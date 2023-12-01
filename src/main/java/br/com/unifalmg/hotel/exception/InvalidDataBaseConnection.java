package br.com.unifalmg.hotel.exception;

public class InvalidDataBaseConnection extends RuntimeException{
    public InvalidDataBaseConnection(String message){
        super(message);
    }
}
