package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository repository;

    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

}
