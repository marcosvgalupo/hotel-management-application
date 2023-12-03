package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Reservation;
import br.com.unifalmg.hotel.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public void saveReservation(Reservation reservation) {
        repository.save(reservation);
    }


}
