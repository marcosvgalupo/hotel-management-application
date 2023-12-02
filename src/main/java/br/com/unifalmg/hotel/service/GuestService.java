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

    public void saveGuest(Guest hospede) {
        repository.save(hospede);
    }

    public void deleteGuest(Integer id) {
        // Verifique se o Guest com o ID fornecido existe antes de tentar deletar
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        // Você pode adicionar lógica adicional, como lançar uma exceção se o Guest não existir
    }

    public List<Guest> findByFilter(String name, String last_name, String cpf, Character gender){
        return repository.findByFilter(name, last_name, cpf, gender);
    }

}
