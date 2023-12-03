/*

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

    // A partir daqui impĺementar a lógica de chave estrangeira para todos os atributos
    @Column(name = "manager_id")
    private Integer manager_id;

    @Column(name = "lodging_id")
    private Integer lodging_id;

    @Column(name = "guest_id")
    private Integer guest_id;

}
*/