package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    @Query("SELECT g FROM Employee g ORDER BY g.name ASC")
    List<Employee> orderEmployeesAtoZ();

    @Query("SELECT g FROM Employee g ORDER BY g.name DESC")
    List<Employee> orderEmployeesZtoA();
}
