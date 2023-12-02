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
            value = "SELECT * FROM employee WHERE " +
                    "(UPPER(:name) is null) or (name = UPPER(:name)) AND " +
                    "(UPPER(:last_name) is null) or (last_name = UPPER(:last_name)) AND" +
                    "(UPPER(:) is null) or (cpf = UPPER(:cpf)) AND " +
                    "(UPPER(:gender) is null) or (gender= UPPER(:gender))"
    )
    List<Guest> findByFilter(String name, String last_name, String cpf, Character gender);
}
