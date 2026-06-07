package pt.estga.exercicio3soapagendamentofinal.dto;

public record AgendamentoRestRequest(
        String data,
        String hora,
        String tipoConsulta
) {
}
