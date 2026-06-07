package pt.estga.exercicio2restconsultas.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import pt.estga.exercicio2restconsultas.dto.ConsultaRequest;
import pt.estga.exercicio2restconsultas.dto.ConsultaResponse;
import pt.estga.exercicio2restconsultas.service.ConsultaService;

@Component
@Path("/consultas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    private final ConsultaService consultaService;

    public ConsultaResource(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @POST
    public Response agendarConsulta(ConsultaRequest request) {
        try {
            ConsultaResponse response = consultaService.agendarConsulta(request);

            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity("{\"erro\":\"" + escape(exception.getMessage()) + "\"}")
                    .build();
        }
    }

    private String escape(String value) {
        return value == null ? "" : value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
