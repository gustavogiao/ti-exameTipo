package pt.estga.exercicio1soaptutores.service;

import org.springframework.stereotype.Service;
import pt.estga.exercicio1soaptutores.domain.Animal;
import pt.estga.exercicio1soaptutores.repository.AnimalRepository;

@Service
public class TutorService {

    private final AnimalRepository repository;

    public TutorService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Animal consultarPorMicrochip(String microchip) {
        validarMicrochip(microchip);

        return repository.findByMicrochip(microchip)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nao existe animal com o microchip " + microchip
                ));
    }

    private void validarMicrochip(String microchip) {
        if (microchip == null || !microchip.matches("\\d{15}")) {
            throw new IllegalArgumentException("O microchip deve conter exatamente 15 digitos caralho.");
        }
    }
}
