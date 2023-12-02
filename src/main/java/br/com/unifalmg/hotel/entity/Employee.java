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

@Table(schema = "railway", name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_employee;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "cnh")
    private String cnh;

    @ManyToOne
    @JoinColumn(name = "id_manager")
    private Manager id_manager;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "cpf")
    private String cpf;
}
