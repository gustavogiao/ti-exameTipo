package pt.estga.exercicio3soapagendamentofinal.dto;

public record ConsultaRestRequest(
        AnimalRestRequest animal,
        TutorRestRequest tutor,
        AgendamentoRestRequest agendamento
) {
}
