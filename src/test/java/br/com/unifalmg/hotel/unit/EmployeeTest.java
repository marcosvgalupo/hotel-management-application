package br.com.unifalmg.hotel.unit;

import br.com.unifalmg.hotel.entity.Employee;
import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.exception.EmployeeNotFoundException;
import br.com.unifalmg.hotel.repository.EmployeeRepository;
import br.com.unifalmg.hotel.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Test
    @DisplayName("#findById > When id is null > Throw an exception")
    void findByIdWhenIdIsNullThrowAnException(){
        assertThrows(IllegalArgumentException.class,()->service.findById(null));
    }

    @Test
    @DisplayName("#findById > When id is not null > When id is not found > Throw an exception")
    void findByIdWhenIdIsNotNullWhenIdIsNotFoundThrowAnException(){
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.findById(2));
    }


    @Test
    @DisplayName("#findById > When id is not null > When id is found > Throw an exception")
    void findByIdWhenIdIsNotNullWhenIdIsFoundThrowAnException(){
        when(repository.findById(2)).thenReturn(Optional.of(
                Employee.builder().employee_id(2).name("Gabriel").last_name("Henrique").cnh("123123123").
                        cpf("12345678912").gender('M').manager_id(new Manager(1,"Calista","Santos",
                                "Calista1","Calista123",'F')).build()));
        Employee response =  service.findById(2);
        assertAll(
                () -> assertEquals(2, response.getEmployee_id()),
                () -> assertEquals("Gabriel", response.getName()),
                () -> assertEquals("Henrique", response.getLast_name()),
                () -> assertEquals("12345678912", response.getCpf()),
                () -> assertEquals('M', response.getGender()),
                () -> assertEquals("123123123", response.getCnh()),
                () -> assertEquals(1, response.getManager_id().getManager_id()),
                () -> assertEquals("Calista", response.getManager_id().getName()),
                () -> assertEquals("Santos", response.getManager_id().getLast_name()),
                () -> assertEquals("Calista1", response.getManager_id().getUsername()),
                () -> assertEquals("Calista123", response.getManager_id().getPassword()),
                () -> assertEquals('F', response.getManager_id().getGender())
                );
    }

    @Test
    @DisplayName("#filteredEmployees > When all filter parameters are null > Throw an exception")
    void filteredEmployeesWhenAllFilterParametersAreNullThrowAnException(){
        Mockito.when(repository.filteredEmployees(null,null,null,null)).
                thenReturn(List.of());
        assertEquals(0, service.filteredEmployees(null, null, null, null).size());
    }

    @Test
    @DisplayName("#filteredEmployees > When at least one is not null > Return the Employee")
    void filteredEmployeesWhenAtLeastOneIsNotNullReturnTheEmployee(){
        Mockito.when(repository.filteredEmployees("Gabriel","Henrique","12345678912",
                        'M')).
                thenReturn(List.of(Employee.builder().employee_id(2).name("Gabriel").last_name("Henrique").cnh("123123123").
                        cpf("12345678912").gender('M').manager_id(new Manager(1,"Calista","Santos",
                                "Calista1","Calista123",'F')).build()));

        List<Employee> response = service.filteredEmployees("Gabriel","Henrique",
                "12345678912",
                'M');
        assertAll(
                () -> assertEquals(1, response.size()),
                () -> assertEquals(2, response.get(0).getEmployee_id()),
                () -> assertEquals("Gabriel", response.get(0).getName()),
                () -> assertEquals("Henrique", response.get(0).getLast_name()),
                () -> assertEquals("12345678912", response.get(0).getCpf()),
                () -> assertEquals('M', response.get(0).getGender()),
                () -> assertEquals("123123123", response.get(0).getCnh()),
                () -> assertEquals(1, response.get(0).getManager_id().getManager_id()),
                () -> assertEquals("Calista", response.get(0).getManager_id().getName()),
                () -> assertEquals("Santos", response.get(0).getManager_id().getLast_name()),
                () -> assertEquals("Calista1", response.get(0).getManager_id().getUsername()),
                () -> assertEquals("Calista123", response.get(0).getManager_id().getPassword()),
                () -> assertEquals('F', response.get(0).getManager_id().getGender())
        );
    }

    @Test
    @DisplayName("#deleteEmployee > When the id is null > Throw an exception")
    void deleteEmployeeWhenTheIdIsNullThrowException(){
        assertThrows(IllegalArgumentException.class, () -> service.deleteEmployee(null));
    }

    @Test
    @DisplayName("#deleteEmployee > When the id is not null > When the employee is not found > Throw an exception")
    void deleteEmployeeWhenIdIsNotNullWhenTheEmployeeIsNotFoundThrowException(){
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee(2));
    }

    @Test
    @DisplayName("#deleteEmployee > When the id is not null > When the employee is found > Delete the employee")
    void deleteEmployeeWhenIdIsNotNullWhenTheEmployeeIsFoundDeleteTheEmployee(){
        when(repository.findById(2)).thenReturn(
                Optional.of(
                        Employee.builder().employee_id(2).name("Gabriel").last_name("Henrique").cnh("123123123").
                                cpf("12345678912").gender('M').manager_id(new Manager(1,"Calista","Santos",
                                        "Calista1","Calista123",'F')).build()
                )
        );

        Employee response = service.findById(2);
        assertAll(
                () -> assertEquals(2, response.getEmployee_id()),
                () -> assertEquals("Gabriel", response.getName()),
                () -> assertEquals("Henrique", response.getLast_name()),
                () -> assertEquals("12345678912", response.getCpf()),
                () -> assertEquals('M', response.getGender()),
                () -> assertEquals("123123123", response.getCnh()),
                () -> assertEquals(1, response.getManager_id().getManager_id()),
                () -> assertEquals("Calista", response.getManager_id().getName()),
                () -> assertEquals("Santos", response.getManager_id().getLast_name()),
                () -> assertEquals("Calista1", response.getManager_id().getUsername()),
                () -> assertEquals("Calista123", response.getManager_id().getPassword()),
                () -> assertEquals('F', response.getManager_id().getGender())
        );
    }



}
