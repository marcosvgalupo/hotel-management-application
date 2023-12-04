package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.Room;
import br.com.unifalmg.hotel.exception.RoomNotFoundException;
import br.com.unifalmg.hotel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    public Room findById(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("Null ID when fetching for a guest.");
        }
        return repository.findById(id).orElseThrow(
                () -> new RoomNotFoundException(String.format("No guest found for id %d", id))
        );
    }

    public List<Room> statusRoom(Integer status) {
        return repository.statusRoom(status);
    }



}
