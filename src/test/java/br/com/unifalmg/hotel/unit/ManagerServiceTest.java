package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.exception.ManagerNotFoundException;
import br.com.unifalmg.hotel.repository.ManagerRepository;
import br.com.unifalmg.hotel.service.ManagerService;
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
public class ManagerServiceTest {
    @InjectMocks
    private ManagerService service;

    @Mock
    private ManagerRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > throw an exception")
    void findByIdWhenTheIdIsNullThrowException(){
        assertThrows(IllegalArgumentException.class,
                () -> service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null and a manager is found > return the manager")
    void findByIdWhenTheIdIsNotNullAndAManagerIsFoundReturnTheManager(){
        when(repository.findById(1)).thenReturn(Optional.of(
                Manager.builder().manager_id(1).name("Marcos").last_name("Vyctor").username("marquin").password("ogrande").gender('M').build()));
        Manager response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getManager_id()),
                () -> assertEquals("Marcos", response.getName()),
                () -> assertEquals("Vyctor", response.getLast_name()),
                () -> assertEquals("marquin", response.getUsername()),
                () -> assertEquals("ogrande", response.getPassword()),
                () -> assertEquals('M', response.getGender())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoManagerIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(ManagerNotFoundException.class, () -> service.findById(2));
    }

    @Test
    @DisplayName("#autenticate > When all autenticate parameters are null > Throw an exception")
    void autenticateWhenAllAutenticateParametersAreNullThrowAnException(){
        assertThrows(IllegalArgumentException.class,
                () -> service.autenticate(null, null));
    }

    @Test
    @DisplayName("autenticate > When at least one autenticate parameter is null > Throw an exception")
    void autenticateWhenAtLeastOneAutenticateParameterIsNullThrowAnException(){
        assertAll("Null Parameters",
                () -> assertThrows(IllegalArgumentException.class, () -> service.autenticate(null, "senha")),
                () -> assertThrows(IllegalArgumentException.class, () -> service.autenticate("usuario", null)),
                () -> assertThrows(IllegalArgumentException.class, () -> service.autenticate(null, null))
        );
    }
}