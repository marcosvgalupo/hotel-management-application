package br.com.unifalmg.hotel.service;

import br.com.unifalmg.hotel.entity.Manager;
import br.com.unifalmg.hotel.exception.InvalidDataBaseConnection;
import br.com.unifalmg.hotel.exception.InvalidManagerException;
import br.com.unifalmg.hotel.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

@Service
@AllArgsConstructor
public class ManagerService {

        @Autowired
        private final ManagerRepository repository;

        public List<Manager> getAllManagers(){
            return repository.findAll();
        }

        public boolean autenticate(String username, String password){
           if(username != null && password != null){
               Manager manager = repository.findByUsernameAndPassword(username, password);
                return manager != null;
           }
           else{
               throw new IllegalArgumentException("Username and password must not be null.");
           }
        }

    public List<Object[]> employeeAndManager() {
        return repository.employeeAndManager();
    }


}
