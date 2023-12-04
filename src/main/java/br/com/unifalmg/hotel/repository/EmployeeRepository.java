package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    @Query(
            "SELECT e FROM Employee e WHERE " +
                    "(:name is null OR UPPER(e.name) LIKE UPPER(concat('%', :name, '%'))) AND " +
                    "(:last_name is null OR UPPER(e.last_name) LIKE UPPER(concat('%', :last_name, '%'))) AND " +
                    "(:cnh is null OR e.cnh LIKE concat('%', :cnh, '%')) AND " +
                    "(:gender is null OR UPPER(e.gender) LIKE UPPER(concat('%', :gender, '%')))"
    )
    List<Employee> filteredEmployees(String name, String last_name, String cnh, Character gender);


    @Query("SELECT g FROM Employee g ORDER BY g.name ASC")
    List<Employee> orderEmployeesAtoZ();

    @Query("SELECT g FROM Employee g ORDER BY g.name DESC")
    List<Employee> orderEmployeesZtoA();
}
