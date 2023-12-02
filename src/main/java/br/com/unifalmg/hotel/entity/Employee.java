package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(schema = "railway", name = "Employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_employee;

    private String name;

    private String last_name;

    private Integer cnh;


    @ManyToOne
    @JoinColumn(name = "id_manager_id_manager")
    private Manager id_manager;


    private Character gender;

    private Integer cpf;
}
