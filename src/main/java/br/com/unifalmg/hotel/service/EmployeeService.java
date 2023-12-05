package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Guest;
import br.com.unifalmg.hotel.exception.EmployeeNotFoundException;
import br.com.unifalmg.hotel.exception.GuestNotFoundException;
import br.com.unifalmg.hotel.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee findById(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("Null ID when fetching for a guest.");
        }
        return repository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(String.format("No employee found for id %d", id))
        );
    }

    public void saveEmployee(Employee employee) {
        repository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        if (!Objects.isNull(findById(id))) {
            repository.deleteById(id);
        }
        throw new EmployeeNotFoundException(String.format("Employee with id[%d] not found!!", id));
    }

    public List<Employee> orderEmployeesAtoZ() {
        return repository.orderEmployeesAtoZ();
    }

    public List<Employee> orderEmployeesZtoA() {
        return repository.orderEmployeesZtoA();
    }

    public List<Employee> filteredEmployees(String name, String last_name, String cnh, Character gender){
        return repository.filteredEmployees(name, last_name, cnh, gender);
    }
}
