package pt.estga.exercicio3soapagendamentofinal.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface AgendamentoFinalEndpoint {

    @WebMethod
    String agendarConsultaFinal(
            @WebParam(name = "microchip") String microchip,
            @WebParam(name = "tipoConsulta") String tipoConsulta,
            @WebParam(name = "data") String data,
            @WebParam(name = "hora") String hora
    );
}
