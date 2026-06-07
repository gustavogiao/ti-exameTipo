package pt.estga.exercicio2restconsultas.service;

import org.springframework.stereotype.Service;
import pt.estga.exercicio2restconsultas.domain.Consulta;
import pt.estga.exercicio2restconsultas.dto.AgendamentoRequest;
import pt.estga.exercicio2restconsultas.dto.AnimalRequest;
import pt.estga.exercicio2restconsultas.dto.ConsultaRequest;
import pt.estga.exercicio2restconsultas.dto.ConsultaResponse;
import pt.estga.exercicio2restconsultas.dto.TutorRequest;
import pt.estga.exercicio2restconsultas.repository.ConsultaRepository;
import pt.estga.exercicio2restconsultas.validation.ConsultaValidator;

import java.time.LocalDateTime;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ConsultaValidator consultaValidator;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaValidator consultaValidator) {
        this.consultaRepository = consultaRepository;
        this.consultaValidator = consultaValidator;
    }

    public ConsultaResponse agendarConsulta(ConsultaRequest request) {
        LocalDateTime dataHoraConsulta = consultaValidator.validar(request);

        AnimalRequest animal = request.animal();
        TutorRequest tutor = request.tutor();
        AgendamentoRequest agendamento = request.agendamento();

        Consulta consulta = new Consulta(
                animal.microchip(),
                animal.nome(),
                animal.especie(),
                animal.raca(),
                tutor.nome(),
                tutor.telefone(),
                agendamento.data(),
                agendamento.hora(),
                agendamento.tipoConsulta()
        );

        consultaRepository.save(consulta);

        return new ConsultaResponse(
                "Consulta agendada com sucesso.",
                animal.microchip(),
                animal.nome(),
                tutor.nome(),
                agendamento.tipoConsulta(),
                dataHoraConsulta.toLocalDate().toString(),
                dataHoraConsulta.toLocalTime().toString()
        );
    }
}
