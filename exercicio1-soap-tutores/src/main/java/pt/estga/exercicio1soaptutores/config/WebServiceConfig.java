package pt.estga.exercicio1soaptutores.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext
    ) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/services/*");
    }

    @Bean(name = "tutors")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema tutorsSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("TutorsPort");
        wsdlDefinition.setLocationUri("/services");
        wsdlDefinition.setTargetNamespace("http://estga.pt/vetsaude/tutors");
        wsdlDefinition.setSchema(tutorsSchema);

        return wsdlDefinition;
    }

    @Bean
    public XsdSchema tutorsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("tutors.xsd"));
    }
}