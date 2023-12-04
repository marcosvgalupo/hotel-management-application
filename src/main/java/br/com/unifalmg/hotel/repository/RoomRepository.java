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

    @Query("UPDATE Room SET manager_id = NULL WHERE reservation_id = :reservation_id")
    void setNullRoomReferencesByReservationId(Integer reservation_id);
    //da para usar essa na de deletar, setando primeiro os valores de chave estrangeira para null,
    // dps apagar a hospedagem correspondete à reserva atual e dps a própria reserva, deixando o quarto disponível mas sem nada vinculado

}
