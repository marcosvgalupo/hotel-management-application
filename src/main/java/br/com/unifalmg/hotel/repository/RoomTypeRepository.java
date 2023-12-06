package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Manager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<Manager, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE RoomType rt SET rt.description = :newDescription WHERE rt.code = :code")
    void updateRoomTypeDescription(@Param("code") Integer code, @Param("newDescription") String newDescription);
}
