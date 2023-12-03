package br.com.unifalmg.hotel.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "railway", name = "lodging")
public class Lodging  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lodging_id;

    @Column(name = "total_price")
    private Float total_price;

    @Column(name = "status")
    private Integer status;
}
