package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

    @Query("SELECT r.manager_id, COUNT(*) as reservationCount FROM Reservation r GROUP BY r.manager_id")
    List<Object[]> countReservationsByManager();

//    @Query(
//            ""
//    )
//    void deleteReservation(Integer id);
}
