package pt.estga.exercicio3soapagendamentofinal.soapclient;

import org.springframework.stereotype.Component;

@Component
public class TutorSoapEnvelopeFactory {

    private static final String NAMESPACE = "http://estga.pt/vetsaude/tutors";

    public String criarPedidoPorMicrochip(String microchip) {
        return """
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tut="%s">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <tut:getTutorByMicrochipRequest>
                         <tut:microchip>%s</tut:microchip>
                      </tut:getTutorByMicrochipRequest>
                   </soapenv:Body>
                </soapenv:Envelope>
                """.formatted(NAMESPACE, XmlEscaper.escape(microchip));
    }
}
