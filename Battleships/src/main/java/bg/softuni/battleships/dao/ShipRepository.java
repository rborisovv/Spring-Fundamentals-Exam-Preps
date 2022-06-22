package bg.softuni.battleships.dao;

import bg.softuni.battleships.model.Ship;
import bg.softuni.battleships.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

}