package pt.estga.exercicio3soapagendamentofinal.soapclient;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import pt.estga.exercicio3soapagendamentofinal.dto.TutorAnimalDto;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

@Component
public class TutorSoapResponseMapper {

    private static final String NAMESPACE = "http://estga.pt/vetsaude/tutors";

    public TutorAnimalDto converter(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document document = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

            return new TutorAnimalDto(
                    texto(document, "nomeTutor"),
                    texto(document, "telefone"),
                    texto(document, "nif"),
                    texto(document, "nomeAnimal"),
                    texto(document, "especie"),
                    texto(document, "raca"),
                    Integer.parseInt(texto(document, "anoNascimento")),
                    texto(document, "microchip")
            );
        } catch (Exception exception) {
            throw new IllegalStateException("Erro ao interpretar resposta SOAP do exercicio 1: " + exception.getMessage(), exception);
        }
    }

    private String texto(Document document, String localName) {
        NodeList nodes = document.getElementsByTagNameNS(NAMESPACE, localName);

        if (nodes.getLength() == 0) {
            throw new IllegalStateException("Campo ausente na resposta SOAP: " + localName);
        }

        return nodes.item(0).getTextContent();
    }
}
