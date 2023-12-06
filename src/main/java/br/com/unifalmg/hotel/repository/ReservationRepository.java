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

    @Query("SELECT m.name as manager_name, g.name as guest_name, r.reservation_id FROM Manager m JOIN Reservation r ON m.manager_id = r.manager_id.manager_id JOIN Guest g ON r.guest_id.guest_id = g.guest_id")
    List<Object[]> getReservationDetails();

    @Query("SELECT l.lodging_id , r.reservation_id FROM Lodging l JOIN Reservation r ON l.lodging_id = r.lodging_id.lodging_id")
    List<Object[]> lodgingAndReservation();

    @Query("SELECT g.name, r.reservation_id, l.total_price " +
            "FROM Guest g " +
            "JOIN Reservation r ON g.guest_id = r.guest_id.guest_id " +
            "JOIN Lodging l ON l.lodging_id = r.lodging_id.lodging_id " +
            "GROUP BY g.guest_id, g.name, r.reservation_id, l.total_price " +
            "HAVING l.total_price > (:price)")
    List<Object[]> reservationPriceGreaterThan(Integer price);

    @Query("SELECT g.name, r.reservation_id, l.total_price " +
            "FROM Guest g " +
            "JOIN Reservation r ON g.guest_id = r.guest_id.guest_id " +
            "JOIN Lodging l ON l.lodging_id = r.lodging_id.lodging_id " +
            "GROUP BY g.guest_id, g.name, r.reservation_id, l.total_price " +
            "HAVING l.total_price < (:price)")
    List<Object[]> reservationPriceLowerThan(Integer price);


}
