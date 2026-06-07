package pt.estga.exercicio3soapagendamentofinal.validation;

import org.springframework.stereotype.Component;

@Component
public class AgendamentoFinalValidator {

    public void validar(String microchip, String tipoConsulta, String data, String hora) {
        if (isBlank(microchip) || !microchip.matches("\\d{15}")) {
            throw new IllegalArgumentException("O microchip deve conter exatamente 15 digitos.");
        }

        if (isBlank(tipoConsulta)) {
            throw new IllegalArgumentException("O tipo de consulta e obrigatorio.");
        }

        if (isBlank(data)) {
            throw new IllegalArgumentException("A data da consulta e obrigatoria.");
        }

        if (isBlank(hora)) {
            throw new IllegalArgumentException("A hora da consulta e obrigatoria.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
