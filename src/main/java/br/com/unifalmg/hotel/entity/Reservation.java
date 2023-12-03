/*
package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "railway", name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservation_id;

    @Column(name = "status")  // se 1, a reserva está em andamento (sendo utilizada agora). se 0, é uma reserva futura
    private Integer status;


    @Column(name = "checkin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin_date;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "checkout_date")
    private Date checkout_date;

    //a partir daqui fazer lógica para chave estrangeira de todos esses atributos
    @Column(name = "manager_id")
    private Integer manager_id;

    @Column(name = "lodging_id")
    private Integer lodging_id;

    @Column(name = "guest_id")
    private Integer guest_id;

    @Column(name = "room_id")
    private Integer room_id;
}
*/