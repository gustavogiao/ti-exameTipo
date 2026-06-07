package pt.estga.exercicio3soapagendamentofinal.config;

import jakarta.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pt.estga.exercicio3soapagendamentofinal.soap.AgendamentoFinalEndpointImpl;

@Component
public class JaxWsPublisher implements CommandLineRunner {

    private final String endpointUrl;
    private final AgendamentoFinalEndpointImpl agendamentoFinalEndpoint;

    public JaxWsPublisher(
            @Value("${vetsaude.services.agendamento.soap}") String endpointUrl,
            AgendamentoFinalEndpointImpl agendamentoFinalEndpoint
    ) {
        this.endpointUrl = endpointUrl;
        this.agendamentoFinalEndpoint = agendamentoFinalEndpoint;
    }

    @Override
    public void run(String... args) {
        Endpoint.publish(endpointUrl, agendamentoFinalEndpoint);
        System.out.println("Servico SOAP de agendamento publicado em: " + endpointUrl + "?wsdl");
    }
}
