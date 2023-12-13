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
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

    @Test
    @DisplayName("#findByFilter > When all filter parameters are null > Throw an exception")
    void findByFilterWhenAllFilterParametersAreNullThrowException(){
        assertThrows(IllegalArgumentException.class,
                () -> service.findByFilter(null, null, null, null));
    }

    @Test
    @DisplayName("#findByFilter > When just one filter parameter is null > Return the corresponding guest")
    void findByFilterWhenJustOneFilterParameterIsNullReturnTheCorrespondingGuest(){

        when(repository.findByFilter("Marcos","Vyctor", null, 'M'))
                .thenReturn(
                    List.of(Guest.builder().name("Marcos").last_name("Vyctor").gender('M').build())
                );
        List<Guest> response = service.findByFilter("Marcos","Vyctor", null, 'M');
        assertAll(
                    () -> assertEquals("Marcos", response.get(0).getName()),
                    () -> assertEquals("Vyctor", response.get(0).getLast_name()),
                    () -> assertEquals('M', response.get(0).getGender())
        );
    }


    @Test
    @DisplayName("#deleteById > When the id is null > Throw an exception")
    void deleteByIdWhenTheIdIsNullThrowException(){
        assertThrows(IllegalArgumentException.class, () -> service.deleteById(null));
    }


    @Test
    @DisplayName("#deleteById > When the id is not null but the guest doesnt exist > Throw an exception")
    void deleteByIdWhenIdIsNotNullAndGuestDoesntExistThrowException(){
        when(repository.findById(1)).thenReturn(Optional.empty()); // significa que o guest com o id 1 não existe
        assertThrows(GuestNotFoundException.class, () -> service.deleteById(1)); //tentando deletar um guest inexistenteSSS
    }

    @Test
    @DisplayName("#deleteById > When the guest exist > Delete the guest")
    void deleteByIdWhenTheGuestExist(){
        when(repository.findById(1)).thenReturn(
                Optional.of(
                        Guest.builder().guest_id(1).name("Marcos").last_name("Vyctor").cpf("1827198271").gender('M').cellphone("3899999").build()
                )
        );
        Guest guestToBeDeleted = service.findById(1);
        assertAll(
                () -> assertEquals(1, guestToBeDeleted.getGuest_id()),
                () -> assertEquals("Marcos", guestToBeDeleted.getName()),
                () -> assertEquals("Vyctor", guestToBeDeleted.getLast_name()),
                () -> assertEquals("1827198271", guestToBeDeleted.getCpf()),
                () -> assertEquals('M', guestToBeDeleted.getGender()),
                () -> assertEquals("3899999", guestToBeDeleted.getCellphone()) // as linhas acima garantem que o guest certo foi encontrado
        );

        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(
                GuestNotFoundException.class,
                () -> service.deleteById(guestToBeDeleted.getGuest_id())
        );
    }


}
