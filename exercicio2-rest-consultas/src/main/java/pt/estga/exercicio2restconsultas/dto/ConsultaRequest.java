package pt.estga.exercicio2restconsultas.dto;

public record ConsultaRequest(
        AnimalRequest animal,
        TutorRequest tutor,
        AgendamentoRequest agendamento
) {
}
