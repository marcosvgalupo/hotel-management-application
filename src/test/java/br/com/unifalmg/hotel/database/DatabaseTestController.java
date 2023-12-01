package br.com.unifalmg.hotel.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("/test")
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/database")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Conexão com o banco de dados bem-sucedida!";
        } catch (Exception e) {
            return "Falha na conexão com o banco de dados: " + e.getMessage();
        }
    }
}
