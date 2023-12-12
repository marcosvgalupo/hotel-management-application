package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.exception.GuestNotFoundException;
import br.com.unifalmg.hotel.repository.GuestRepository;
import br.com.unifalmg.hotel.service.GuestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

    @InjectMocks
    private GuestService service; // tipo de objeto que queremos simular

    @Mock
    private GuestRepository repository; // onde serão simulados

//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);  // usar na ausência de @ExtendWith -- reponsável pela inicialização e gerenciamento dos mocks
//    }

    @Test
    @DisplayName("#findById > When the id is null > throw an exception")
    void findByIdWhenTheIdIsNullThrowException(){
        assertThrows(IllegalArgumentException.class,
                () -> service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null and a guest is found > return the guest")
    void findByIdWhenTheIdIsNotNullAndAGuestIsFoundReturnTheGuest(){
        when(repository.findById(1)).thenReturn(Optional.of(
                Guest.builder().guest_id(1).name("Marcos").last_name("Vyctor").cpf("1827198271").gender('M').cellphone("3899999").build()));
                Guest response = service.findById(1);
                assertAll(
                        () -> assertEquals(1, response.getGuest_id()),
                        () -> assertEquals("Marcos", response.getName()),
                        () -> assertEquals("Vyctor", response.getLast_name()),
                        () -> assertEquals("1827198271", response.getCpf()),
                        () -> assertEquals('M', response.getGender()),
                        () -> assertEquals("3899999", response.getCellphone())
                );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoGuestIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(GuestNotFoundException.class, () -> service.findById(2));
    }

}
