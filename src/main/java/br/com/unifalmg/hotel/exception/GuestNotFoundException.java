package br.com.unifalmg.hotel.exception;

public class GuestNotFoundException extends RuntimeException{
    public GuestNotFoundException(String message){ super(message); }
}
