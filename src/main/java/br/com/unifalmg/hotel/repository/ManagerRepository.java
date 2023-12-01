package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM Manager WHERE " +
                "UPPER(username) = UPPER(:username) " + " AND " + " UPPER(password) = UPPER(:password)"
    )
    Manager findByUsernameAndPassword(String username, String password);
}
