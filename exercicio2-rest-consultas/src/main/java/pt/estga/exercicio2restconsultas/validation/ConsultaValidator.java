package pt.estga.exercicio2restconsultas.validation;

import org.springframework.stereotype.Component;
import pt.estga.exercicio2restconsultas.dto.AgendamentoRequest;
import pt.estga.exercicio2restconsultas.dto.AnimalRequest;
import pt.estga.exercicio2restconsultas.dto.ConsultaRequest;
import pt.estga.exercicio2restconsultas.dto.TutorRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Component
public class ConsultaValidator {

    public LocalDateTime validar(ConsultaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("O pedido nao pode estar vazio.");
        }

        if (request.animal() == null) {
            throw new IllegalArgumentException("Os dados do animal sao obrigatorios.");
        }

        if (request.tutor() == null) {
            throw new IllegalArgumentException("Os dados do tutor sao obrigatorios.");
        }

        if (request.agendamento() == null) {
            throw new IllegalArgumentException("Os dados do agendamento sao obrigatorios.");
        }

        validarAnimal(request.animal());
        validarTutor(request.tutor());
        validarAgendamento(request.agendamento());

        return validarDataHoraFutura(
                request.agendamento().data(),
                request.agendamento().hora()
        );
    }

    private void validarAnimal(AnimalRequest animal) {
        if (isBlank(animal.microchip()) || !animal.microchip().matches("\\d{15}")) {
            throw new IllegalArgumentException("O microchip deve conter exatamente 15 digitos.");
        }

        if (isBlank(animal.nome())) {
            throw new IllegalArgumentException("O nome do animal e obrigatorio.");
        }

        if (isBlank(animal.especie())) {
            throw new IllegalArgumentException("A especie do animal e obrigatoria.");
        }

        if (isBlank(animal.raca())) {
            throw new IllegalArgumentException("A raca do animal e obrigatoria.");
        }
    }

    private void validarTutor(TutorRequest tutor) {
        if (isBlank(tutor.nome())) {
            throw new IllegalArgumentException("O nome do tutor e obrigatorio.");
        }

        if (isBlank(tutor.telefone()) || !tutor.telefone().matches("\\d{9}")) {
            throw new IllegalArgumentException("O telefone deve conter exatamente 9 digitos.");
        }
    }

    private void validarAgendamento(AgendamentoRequest agendamento) {
        if (isBlank(agendamento.data())) {
            throw new IllegalArgumentException("A data da consulta e obrigatoria.");
        }

        if (isBlank(agendamento.hora())) {
            throw new IllegalArgumentException("A hora da consulta e obrigatoria.");
        }

        if (isBlank(agendamento.tipoConsulta())) {
            throw new IllegalArgumentException("O tipo de consulta e obrigatorio.");
        }
    }

    private LocalDateTime validarDataHoraFutura(String data, String hora) {
        try {
            LocalDate dataConsulta = LocalDate.parse(data);
            LocalTime horaConsulta = LocalTime.parse(hora);
            LocalDateTime dataHoraConsulta = LocalDateTime.of(dataConsulta, horaConsulta);

            if (!dataHoraConsulta.isAfter(LocalDateTime.now())) {
                throw new IllegalArgumentException("A consulta deve ser agendada para uma data e hora futuras.");
            }

            return dataHoraConsulta;
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("A data deve estar no formato yyyy-MM-dd e a hora no formato HH:mm.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
