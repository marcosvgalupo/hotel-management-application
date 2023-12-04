package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

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

    @Column(name = "status")
    private Integer status;

    @Column(name = "checkin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private java.sql.Date checkin_date;

    @Column(name = "checkout_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date checkout_date;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager_id;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging_id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest_id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room_id;
}
