package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.entity.RoomType;
import br.com.unifalmg.hotel.exception.RoomTypeNotFoundException;
import br.com.unifalmg.hotel.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomTypeService {
    private RoomTypeRepository repository;

    public List<RoomType> getAllRoomTypes() {
        return repository.findAll();
    }

    public RoomType findById(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("Null ID when fetching for a guest.");
        }
        return repository.findById(id).orElseThrow(
                () -> new RoomTypeNotFoundException(String.format("No guest found for id %d", id))
        );
    }

    public void saveRoomType(RoomType roomType) {
        repository.save(roomType);
    }

    public void updateRoomTypeDescription(Integer code, String newDescription) {
        repository.updateRoomTypeDescription(code, newDescription);
    }

}
