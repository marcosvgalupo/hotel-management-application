package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

    @Query("SELECT r.manager_id, COUNT(*) as reservationCount FROM Reservation r GROUP BY r.manager_id")
    List<Object[]> countReservationsByManager();

    @Query(
            "UPDATE Reservation SET checkin_date = :checkin_date, checkout_date = :checkout_date WHERE reservation_id = :id"
    )
    void saveUpdateReservation(Integer id, Date checkin_date, Date checkout_date);

}
