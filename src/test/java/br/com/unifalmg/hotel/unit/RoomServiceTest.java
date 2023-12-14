package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.Reservation;
import br.com.unifalmg.hotel.entity.Room;
import br.com.unifalmg.hotel.exception.RoomNotFoundException;
import br.com.unifalmg.hotel.repository.RoomRepository;
import br.com.unifalmg.hotel.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import java.util.Optional;

public class RoomServiceTest {
    @InjectMocks
    private RoomService service;

    @Mock
    private RoomRepository repository;

    @BeforeEach
    public void setup (){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    @DisplayName("#findById > When id is NULL > Throw an Exception")
    void findByIdWhenIdIsNULLThrowAnException(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->service.findById(null));
    }

    @Test
    @DisplayName("#findById > When id is not found > Throw an Exeception")
    void findByIdWhenIdIsNotFoundThrowAnException(){
        Mockito.when(repository.findById(3)).thenReturn(Optional.empty());
        Assertions.assertThrows(RoomNotFoundException.class,
                ()->service.findById(3));
    }

    @Test
    @DisplayName("#findById > When the id is found > Return Room")
    void findByIdeWhenTheIdIsFoundReturnRoom(){
        Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(Room.builder().room_number(1)
                .number_of_beds(2).number_of_bathrooms(1).room_type_code(1).status(1)
                .lodging_id(1).build()));
        Room quarto = service.findById(1);
        Assertions.assertAll(
                ()->Assertions.assertEquals(1,quarto.getRoom_number()),
                ()->Assertions.assertEquals(2,quarto.getNumber_of_beds()),
                ()->Assertions.assertEquals(1,quarto.getNumber_of_bathrooms()),
                ()->Assertions.assertEquals(1,quarto.getRoom_type_code()),
                ()->Assertions.assertEquals(1,quarto.getStatus()),
                ()->Assertions.assertEquals(1,quarto.getLodging_id())

        );
    }

}
