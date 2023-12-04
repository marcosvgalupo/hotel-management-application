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
}
