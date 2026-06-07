package pt.estga.exercicio1soaptutores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estga.exercicio1soaptutores.domain.Animal;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByMicrochip(String microchip);
}