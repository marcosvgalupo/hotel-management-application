package br.com.unifalmg.hotel.exception;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(String message){ super(message); }
}
