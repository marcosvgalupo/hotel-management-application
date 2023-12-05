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


//    @Query(
//            ""
//    )
//    void deleteGuest(Integer id);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Guest WHERE guest_id = :guest_id")
//    void deleteByGuestId(@Param("guest_id") Integer guest_id);
//
//    @Modifying
//    @Query("DELETE FROM Reservation r WHERE r.guest_id = :guest_id")
//    void deleteReservationByGuestId(Integer guest_id);

//    @Modifying
//    @Query("DELETE FROM Room r WHERE r.guest_id = :guest_id")
//    void deleteRoomByGuestId(Integer guest_id);

//    @Modifying
//    @Query("DELETE FROM Lodging l WHERE l.lodging_id IN (SELECT r.lodging_id FROM Reservation r WHERE r.guest_id = :guest_id)")
//    void deleteLodgingByGuestId(Integer guest_id);
//
//    @Modifying
//    @Query("UPDATE Room r SET r.guest_id = NULL, r.manager_id = NULL, r.lodging_id = NULL, r.reservation_id = NULL WHERE r.guest_id = :guest_id")
//    void updateRoomByGuestId(Integer guest_id);

    //essa ta certa (testei no mysql)
//    @Modifying
//    @Transactional
//    @Query("UPDATE Room SET reservation_id = NULL, lodging_id = NULL WHERE reservation_id IN (SELECT reservation_id FROM Reservation WHERE guest_id = :id)")
//    void setRoomNullFKs(Integer id);
//
//
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Lodging WHERE lodging_id IN (SELECT r.lodging_id FROM Reservation r WHERE r.guest_id = :id)")
//    void deleteLodgingByGuestId(@Param("id") Integer id);
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Guest g WHERE g.guest_id = :id")
//    void deleteGuest(@Param("id") Integer id);

}
