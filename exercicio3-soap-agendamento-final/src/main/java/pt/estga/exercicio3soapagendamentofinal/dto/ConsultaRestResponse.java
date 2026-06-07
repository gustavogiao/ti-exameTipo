package pt.estga.exercicio3soapagendamentofinal.dto;

public record ConsultaRestResponse(
        String mensagem,
        String microchip,
        String nomeAnimal,
        String nomeTutor,
        String tipoConsulta,
        String data,
        String hora
) {
}
