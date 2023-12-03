package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Integer>{

    @Query(
            nativeQuery = true,
            value = "SELECT name, last_name, cpf, gender FROM guest WHERE " +
                    "(:name is null or UPPER(name) = UPPER(:name)) AND " +
                    "(:last_name is null or UPPER(last_name) = UPPER(:last_name)) AND " +
                    "(:cpf is null or cpf = :cpf) AND " +
                    "(:gender is null or UPPER(gender) = UPPER(:gender))"
    )
    List<GuestProjection> findByFilter(String name, String last_name, String cpf, Character gender);

    interface GuestProjection {
        String getName();
        String getLastName();
        String getCpf();
        String getCellphone();
        Character getGender();
    }
}
