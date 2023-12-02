package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "railway", name = "Manager")
public class Manager implements Serializable {

    @Id
    private Integer id_manager;

    private String name;

    private String last_name;

    private String username;

    private String password;

    private Character gender;
}
