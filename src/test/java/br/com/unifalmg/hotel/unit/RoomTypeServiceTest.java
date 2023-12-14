package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.RoomType;
import br.com.unifalmg.hotel.exception.RoomTypeNotFoundException;
import br.com.unifalmg.hotel.repository.RoomTypeRepository;
import br.com.unifalmg.hotel.service.RoomTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomTypeServiceTest {

    @InjectMocks
    private RoomTypeService service;

    @Mock
    private RoomTypeRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > throw an exception")
    void findByIdWhenTheIdIsNullThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null and a RoomType is found > return the RoomType")
    void findByIdWhenTheIdIsNotNullAndARoomTypeIsFoundReturnTheRoomType() {
        when(repository.findById(1)).thenReturn(Optional.of(
                RoomType.builder().code(1).description("quarto suíte").build()));
        RoomType response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getCode()),
                () -> assertEquals("quarto suíte", response.getDescription())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no RoomType is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoRoomTypeIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(RoomTypeNotFoundException.class, () -> service.findById(2));
    }

    @Test
    @DisplayName("#saveRoomType > When a room type is saved > Verify that the repository's save method is called")
    void saveRoomTypeWhenRoomTypeIsSavedVerifyRepositorySaveMethod() {
        RoomType roomType = RoomType.builder().code(1).description("quarto suíte").build();
        service.saveRoomType(roomType);
        verify(repository, times(1)).save(roomType);
    }
}