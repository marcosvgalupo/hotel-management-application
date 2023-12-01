package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.exception.GuestNotFoundException;
import br.com.unifalmg.hotel.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository repository;

    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

    public Guest findById(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("Null ID when fetching for a guest.");
        }
        return repository.findById(id).orElseThrow(
                () -> new GuestNotFoundException(String.format("No guest found for id %d", id))
        );
    }


}
