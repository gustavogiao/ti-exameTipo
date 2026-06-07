package pt.estga.exercicio1soaptutores.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pt.estga.exercicio1soaptutores.domain.Animal;
import pt.estga.exercicio1soaptutores.domain.Tutor;
import pt.estga.exercicio1soaptutores.service.TutorService;
import pt.estga.exercicio1soaptutores.soap.generated.GetTutorByMicrochipRequest;
import pt.estga.exercicio1soaptutores.soap.generated.GetTutorByMicrochipResponse;
import pt.estga.exercicio1soaptutores.soap.generated.TutorAnimalType;

@Endpoint
public class TutorEndpoint {

    private static final String NAMESPACE_URI = "http://estga.pt/vetsaude/tutors";

    private final TutorService tutorService;

    public TutorEndpoint(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTutorByMicrochipRequest")
    @ResponsePayload
    public GetTutorByMicrochipResponse getTutorByMicrochip(
            @RequestPayload GetTutorByMicrochipRequest request
    ) {
        Animal animal = tutorService.consultarPorMicrochip(request.getMicrochip());
        Tutor tutor = animal.getTutor();

        TutorAnimalType tutorAnimal = new TutorAnimalType();
        tutorAnimal.setNomeTutor(tutor.getNome());
        tutorAnimal.setTelefone(tutor.getTelefone());
        tutorAnimal.setNif(tutor.getNif());
        tutorAnimal.setNomeAnimal(animal.getNome());
        tutorAnimal.setEspecie(animal.getEspecie());
        tutorAnimal.setRaca(animal.getRaca());
        tutorAnimal.setAnoNascimento(animal.getAnoNascimento());
        tutorAnimal.setMicrochip(animal.getMicrochip());

        GetTutorByMicrochipResponse response = new GetTutorByMicrochipResponse();
        response.setTutorAnimal(tutorAnimal);

        return response;
    }
}
