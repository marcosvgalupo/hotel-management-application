package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.exception.EmployeeNotFoundException;
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

}
