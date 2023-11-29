package br.com.unifalmg.hotel.repository;

import br.com.unifalmg.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Integer>{
}
