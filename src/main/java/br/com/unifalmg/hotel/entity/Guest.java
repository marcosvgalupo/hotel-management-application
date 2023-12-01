package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "railway", name = "Guest")
public class Guest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHospede")
    private Integer id;

    private String name;

    private String last_name;

    private Integer status;

    private String cpf;

    private Character sex;

    private String cellphone;

}
