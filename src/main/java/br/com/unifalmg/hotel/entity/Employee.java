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
    private Integer idFuncionario;

    private String nome;

    private String sobrenome;

    private Integer cnh;

    @ManyToOne
    @JoinColumn(name = "idGerente")
    private Manager id_manager;

    private Character sexo;

    private Integer cpf;
}
