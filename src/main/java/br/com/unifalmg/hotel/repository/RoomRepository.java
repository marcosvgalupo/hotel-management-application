package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r EXCEPT SELECT r FROM Room r WHERE r.status = :status")
    List<Room> statusRoom(Integer status);

    @Query("SELECT r.room_number, c.code FROM Room r JOIN RoomType c ON r.room_type_code = c.code")
    List<Object[]> roomsAndTypes();

    @Query("""
            SELECT rt.code AS room_type, COUNT(r.reservation_id) AS total_reservations\s
            FROM Room r\s
            JOIN RoomType rt ON r.room_type_code = rt.code\s
            GROUP BY rt.code\s
            ORDER BY total_reservations DESC\s
            """)
    List<Object[]> reservationsByRoomType();


}
