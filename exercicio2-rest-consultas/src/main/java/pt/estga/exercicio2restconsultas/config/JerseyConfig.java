package pt.estga.exercicio2restconsultas.config;

import org.glassfish.jersey.gson.JsonGsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("pt.estga.exercicio2restconsultas.resource");
        register(JsonGsonFeature.class);
    }
}
