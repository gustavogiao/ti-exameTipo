package pt.estga.exercicio2restconsultas.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String microchip;

    private String nomeAnimal;

    private String especie;

    private String raca;

    private String nomeTutor;

    private String telefoneTutor;

    private String dataConsulta;

    private String horaConsulta;

    private String tipoConsulta;

    public Consulta(
            String microchip,
            String nomeAnimal,
            String especie,
            String raca,
            String nomeTutor,
            String telefoneTutor,
            String dataConsulta,
            String horaConsulta,
            String tipoConsulta
    ) {
        this.microchip = microchip;
        this.nomeAnimal = nomeAnimal;
        this.especie = especie;
        this.raca = raca;
        this.nomeTutor = nomeTutor;
        this.telefoneTutor = telefoneTutor;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.tipoConsulta = tipoConsulta;
    }

}
