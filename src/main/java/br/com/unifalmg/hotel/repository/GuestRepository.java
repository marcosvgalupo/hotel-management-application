package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Guest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Integer>{

    @Query(
            "SELECT g FROM Guest g WHERE " +
            "(:name is null OR UPPER(g.name) LIKE UPPER(concat('%', :name, '%'))) AND " +
            "(:last_name is null OR UPPER(g.last_name) LIKE UPPER(concat('%', :last_name, '%'))) AND " +
            "(:cpf is null OR g.cpf LIKE concat('%', :cpf, '%')) AND " +
            "(:gender is null OR UPPER(g.gender) LIKE UPPER(concat('%', :gender, '%')))"
    )
    List<Guest> findByFilter(String name, String last_name, String cpf, Character gender);

    @Query("SELECT g FROM Guest g ORDER BY g.name ASC")
    List<Guest> orderGuestsAtoZ();

    @Query("SELECT g FROM Guest g ORDER BY g.name DESC")
    List<Guest> orderGuestsZtoA();

    @Query("SELECT g.name, g.last_name, r.reservation_id, r.status FROM Guest g JOIN Reservation r ON g.guest_id = r.guest_id.guest_id WHERE g.guest_id = :guest_id")
    List<Object[]> selectGuestAndYoursReservationsByGuestId(Integer guest_id);

    @Query("SELECT g.guest_id, g.name, g.last_name, r.reservation_id FROM Guest g JOIN Reservation r ON g.guest_id = r.guest_id.guest_id WHERE r.lodging_id.lodging_id = :id")
    List<Object[]> selectGuestAndReservationByLodgingId(Integer id);



    @Query("SELECT g.name, SUM(l.total_price) AS totalExpenses " +
            "FROM Reservation r " +
            "JOIN Guest g ON r.guest_id.guest_id = g.guest_id " +
            "JOIN Lodging l ON r.lodging_id.lodging_id = l.lodging_id " +
            "GROUP BY g.name " +
            "ORDER BY totalExpenses DESC")
    List<Object[]> findGuestsWithHighestExpenses();

    @Query("SELECT g.name, SUM(l.total_price) AS totalExpenses " +
            "FROM Reservation r " +
            "JOIN Guest g ON r.guest_id.guest_id = g.guest_id " +
            "JOIN Lodging l ON r.lodging_id.lodging_id = l.lodging_id " +
            "GROUP BY g.name " +
            "ORDER BY totalExpenses ASC")
    List<Object[]> findGuestsWithLowestExpenses();

    @Query("SELECT g.name, AVG(l.total_price) AS avgExpense \n" +
            "FROM Guest AS g \n" +
            "JOIN Reservation AS r ON g.guest_id = r.guest_id.guest_id \n" +
            "JOIN Lodging AS l ON r.lodging_id.lodging_id = l.lodging_id \n" +
            "GROUP BY g.name")
    List<Object[]> AvgPriceByGuest();


}
