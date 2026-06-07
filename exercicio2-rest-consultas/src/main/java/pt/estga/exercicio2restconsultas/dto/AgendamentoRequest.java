package pt.estga.exercicio2restconsultas.dto;

public record AgendamentoRequest(
        String data,
        String hora,
        String tipoConsulta
) {
}
