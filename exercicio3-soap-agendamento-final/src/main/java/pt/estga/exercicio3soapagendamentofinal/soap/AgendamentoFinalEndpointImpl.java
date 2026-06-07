package pt.estga.exercicio3soapagendamentofinal.soap;

import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import pt.estga.exercicio3soapagendamentofinal.service.AgendamentoFinalService;

@Component
@WebService(endpointInterface = "pt.estga.exercicio3soapagendamentofinal.soap.AgendamentoFinalEndpoint")
public class AgendamentoFinalEndpointImpl implements AgendamentoFinalEndpoint {

    private final AgendamentoFinalService agendamentoFinalService;

    public AgendamentoFinalEndpointImpl(AgendamentoFinalService agendamentoFinalService) {
        this.agendamentoFinalService = agendamentoFinalService;
    }

    @Override
    public String agendarConsultaFinal(String microchip, String tipoConsulta, String data, String hora) {
        try {
            return agendamentoFinalService.agendarConsultaFinal(microchip, tipoConsulta, data, hora);
        } catch (Exception exception) {
            return "Erro: " + exception.getMessage();
        }
    }
}
