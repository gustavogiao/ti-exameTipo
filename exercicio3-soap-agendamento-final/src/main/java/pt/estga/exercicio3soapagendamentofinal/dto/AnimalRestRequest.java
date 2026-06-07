package pt.estga.exercicio3soapagendamentofinal.dto;

public record AnimalRestRequest(
        String microchip,
        String nome,
        String especie,
        String raca
) {
}
