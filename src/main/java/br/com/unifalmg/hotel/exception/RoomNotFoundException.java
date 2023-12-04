package br.com.unifalmg.hotel.exception;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(String message){ super(message); }
}
