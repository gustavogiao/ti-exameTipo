package pt.estga.exercicio3soapagendamentofinal.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.estga.exercicio3soapagendamentofinal.dto.AgendamentoRestRequest;
import pt.estga.exercicio3soapagendamentofinal.dto.AnimalRestRequest;
import pt.estga.exercicio3soapagendamentofinal.dto.ConsultaRestRequest;
import pt.estga.exercicio3soapagendamentofinal.dto.ConsultaRestResponse;
import pt.estga.exercicio3soapagendamentofinal.dto.TutorAnimalDto;
import pt.estga.exercicio3soapagendamentofinal.dto.TutorRestRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsultaRestClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String consultasRestUrl;

    public ConsultaRestClient(@Value("${vetsaude.services.consultas.rest}") String consultasRestUrl) {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.consultasRestUrl = consultasRestUrl;
    }

    public ConsultaRestResponse agendarConsulta(
            TutorAnimalDto tutorAnimal,
            String tipoConsulta,
            String data,
            String hora
    ) {
        try {
            ConsultaRestRequest consultaRequest = new ConsultaRestRequest(
                    new AnimalRestRequest(
                            tutorAnimal.microchip(),
                            tutorAnimal.nomeAnimal(),
                            tutorAnimal.especie(),
                            tutorAnimal.raca()
                    ),
                    new TutorRestRequest(
                            tutorAnimal.nomeTutor(),
                            tutorAnimal.telefone()
                    ),
                    new AgendamentoRestRequest(data, hora, tipoConsulta)
            );

            String json = objectMapper.writeValueAsString(consultaRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(consultasRestUrl))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Servico REST de consultas devolveu HTTP "
                        + response.statusCode() + ": " + response.body());
            }

            return objectMapper.readValue(response.body(), ConsultaRestResponse.class);
        } catch (Exception exception) {
            throw new IllegalStateException("Erro ao agendar consulta no servico REST do exercicio 2: " + exception.getMessage(), exception);
        }
    }
}
