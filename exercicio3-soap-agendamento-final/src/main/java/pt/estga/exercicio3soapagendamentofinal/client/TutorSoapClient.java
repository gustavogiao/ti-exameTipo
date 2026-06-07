package pt.estga.exercicio3soapagendamentofinal.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.estga.exercicio3soapagendamentofinal.dto.TutorAnimalDto;
import pt.estga.exercicio3soapagendamentofinal.soapclient.TutorSoapEnvelopeFactory;
import pt.estga.exercicio3soapagendamentofinal.soapclient.TutorSoapResponseMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TutorSoapClient {

    private final HttpClient httpClient;
    private final TutorSoapEnvelopeFactory envelopeFactory;
    private final TutorSoapResponseMapper responseMapper;
    private final String tutorsServiceUrl;

    public TutorSoapClient(
            @Value("${vetsaude.services.tutors.wsdl}") String tutorsWsdlUrl,
            TutorSoapEnvelopeFactory envelopeFactory,
            TutorSoapResponseMapper responseMapper
    ) {
        this.httpClient = HttpClient.newHttpClient();
        this.envelopeFactory = envelopeFactory;
        this.responseMapper = responseMapper;
        this.tutorsServiceUrl = tutorsWsdlUrl.replace("/tutors.wsdl", "");
    }

    public TutorAnimalDto consultarPorMicrochip(String microchip) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tutorsServiceUrl))
                    .header("Content-Type", "text/xml;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(envelopeFactory.criarPedidoPorMicrochip(microchip)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Servico SOAP de tutores devolveu HTTP " + response.statusCode() + ".");
            }

            return responseMapper.converter(response.body());
        } catch (Exception exception) {
            throw new IllegalStateException("Erro ao consultar dados do tutor no servico SOAP do exercicio 1: " + exception.getMessage(), exception);
        }
    }
}
