package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "railway", name = "Manager")
public class Manager extends Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Manager id_manager;

    private String name;

    private String last_name;

    private String username;

    private String password;

    private Character gender;
}
