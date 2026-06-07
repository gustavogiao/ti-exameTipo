package pt.estga.exercicio3soapagendamentofinal.service;

import org.springframework.stereotype.Service;
import pt.estga.exercicio3soapagendamentofinal.client.ConsultaRestClient;
import pt.estga.exercicio3soapagendamentofinal.client.TutorSoapClient;
import pt.estga.exercicio3soapagendamentofinal.dto.ConsultaRestResponse;
import pt.estga.exercicio3soapagendamentofinal.dto.TutorAnimalDto;
import pt.estga.exercicio3soapagendamentofinal.validation.AgendamentoFinalValidator;

@Service
public class AgendamentoFinalService {

    private final TutorSoapClient tutorSoapClient;
    private final ConsultaRestClient consultaRestClient;
    private final AgendamentoFinalValidator validator;

    public AgendamentoFinalService(
            TutorSoapClient tutorSoapClient,
            ConsultaRestClient consultaRestClient,
            AgendamentoFinalValidator validator
    ) {
        this.tutorSoapClient = tutorSoapClient;
        this.consultaRestClient = consultaRestClient;
        this.validator = validator;
    }

    public String agendarConsultaFinal(String microchip, String tipoConsulta, String data, String hora) {
        validator.validar(microchip, tipoConsulta, data, hora);

        TutorAnimalDto tutorAnimal = tutorSoapClient.consultarPorMicrochip(microchip);
        ConsultaRestResponse consulta = consultaRestClient.agendarConsulta(tutorAnimal, tipoConsulta, data, hora);

        return "Consulta agendada com sucesso. "
                + "Tutor: " + tutorAnimal.nomeTutor() + "; "
                + "Animal: " + tutorAnimal.nomeAnimal() + "; "
                + "Especie: " + tutorAnimal.especie() + "; "
                + "Raca: " + tutorAnimal.raca() + "; "
                + "Tipo de consulta: " + consulta.tipoConsulta() + "; "
                + "Data: " + consulta.data() + "; "
                + "Hora: " + consulta.hora() + ".";
    }
}
