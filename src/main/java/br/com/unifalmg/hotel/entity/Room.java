package br.com.unifalmg.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "railway", name = "room")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer room_number;

    @Column(name = "number_of_beds")
    private Integer number_of_beds;

    @Column(name = "number_of_bathrooms")
    private Integer number_of_bathrooms;

    @Column(name = "room_type_code")
    private Integer room_type_code;

    @Column(name = "status") // se 1 o quarto está disponível, se 0 está ocupado
    private Integer status;

    @Column(name = "manager_id")
    private Integer manager_id;

    @Column(name = "lodging_id")
    private Integer lodging_id;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Guest guest_id;


}
