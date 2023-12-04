package br.com.unifalmg.hotel.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(String message){ super(message); }
}
