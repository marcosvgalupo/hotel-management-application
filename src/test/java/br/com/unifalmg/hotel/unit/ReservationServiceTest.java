package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.*;
import br.com.unifalmg.hotel.exception.ReservationNotFoundException;
import br.com.unifalmg.hotel.repository.ReservationRepository;
import br.com.unifalmg.hotel.service.ReservationService;
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
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService service;

    @Mock
    private ReservationRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > throw an exception")
    void findByIdWhenTheIdIsNullThrowException() {
        assertThrows(IllegalArgumentException.class, () -> service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null and a reservation is found > return the reservation")
    void findByIdWhenTheIdIsNotNullAndAReservationIsFoundReturnTheReservation() {
        when(repository.findById(1)).thenReturn(Optional.of(
                Reservation.builder().reservation_id(1).status(2).checkin_date("05/05/2023").checkout_date("07/05/2023").build()));
        Reservation response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getReservation_id()),
                () -> assertEquals(2, response.getStatus()),
                () -> assertEquals("05/05/2023", response.getCheckin_date()),
                () -> assertEquals("07/05/2023", response.getCheckout_date())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no reservation is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoReservationIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(ReservationNotFoundException.class, () -> service.findById(2));
    }

    @Test
    @DisplayName("#cancelReservation > When canceling an existing reservation > Reservation should be canceled")
    void cancelExistingReservationShouldCancel() {
        int existingReservationId = 1;
        when(repository.existsById(existingReservationId)).thenReturn(true);
        service.cancelReservation(existingReservationId);
        verify(repository, times(1)).deleteById(existingReservationId);
    }

    @Test
    @DisplayName("#cancelReservation > When canceling a non-existing reservation > Nothing should happen")
    void cancelNonExistingReservationShouldDoNothing() {
        int nonExistingReservationId = 2;
        when(repository.existsById(nonExistingReservationId)).thenReturn(false);
        assertDoesNotThrow(() -> service.cancelReservation(nonExistingReservationId));
        verify(repository, never()).deleteById(nonExistingReservationId);
    }
}
