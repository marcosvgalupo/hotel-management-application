package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.repository.ManagerRepository;
import br.com.unifalmg.hotel.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomTypeService {
    private RoomTypeRepository roomTypeRepository;

    public void updateRoomTypeDescription(Integer code, String newDescription) {
        roomTypeRepository.updateRoomTypeDescription(code, newDescription);
    }
}
