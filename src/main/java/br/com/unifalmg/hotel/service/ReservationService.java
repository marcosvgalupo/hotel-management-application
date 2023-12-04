package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Reservation;
import br.com.unifalmg.hotel.exception.ReservationNotFoundException;
import br.com.unifalmg.hotel.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Date;

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

    public void cancelReservation(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public Reservation findById(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("Null ID when fetching for a repository.");
        }
        return repository.findById(id).orElseThrow(
                () -> new ReservationNotFoundException(String.format("No repository found for id %d", id))
        );
    }

    public List<Object[]> countReservationsByManager() {
        return repository.countReservationsByManager();
    }


    public void saveUpdateReservation(Integer id, Date checkin_date, Date checkout_date){
        repository.saveUpdateReservation(id, checkin_date, checkout_date);
    }

}
