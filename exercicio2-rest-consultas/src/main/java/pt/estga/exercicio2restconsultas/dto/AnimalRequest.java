package pt.estga.exercicio2restconsultas.dto;

public record AnimalRequest(
        String microchip,
        String nome,
        String especie,
        String raca
) {
}
