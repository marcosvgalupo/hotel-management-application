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
    private Integer employee_id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "cnh")
    private String cnh;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager_id;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "cpf")
    private String cpf;
}
