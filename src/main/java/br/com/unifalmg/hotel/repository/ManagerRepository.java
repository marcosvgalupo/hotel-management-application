package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Manager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM manager WHERE " +
                "UPPER(username) = UPPER(:username) " + " AND " + " UPPER(password) = UPPER(:password)"
    )
    Manager findByUsernameAndPassword(String username, String password);

    @Query("SELECT g.name as name_manager,e.name as name_employee FROM Manager g JOIN Employee e ON g.manager_id = e.manager_id.manager_id")
    List<Object[]> employeeAndManager();

    @Modifying
    @Transactional
    @Query("UPDATE Manager m SET m.username = :username WHERE m.password = :password")
    void updateManager(@Param("username") String username, @Param("password") String password);
}
