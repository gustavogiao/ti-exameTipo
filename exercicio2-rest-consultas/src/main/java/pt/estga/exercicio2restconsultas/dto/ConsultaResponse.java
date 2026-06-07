package pt.estga.exercicio2restconsultas.dto;

public record ConsultaResponse(
        String mensagem,
        String microchip,
        String nomeAnimal,
        String nomeTutor,
        String tipoConsulta,
        String data,
        String hora
) {
}
