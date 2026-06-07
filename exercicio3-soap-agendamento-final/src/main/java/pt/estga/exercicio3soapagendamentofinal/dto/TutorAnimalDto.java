package pt.estga.exercicio3soapagendamentofinal.dto;

public record TutorAnimalDto(
        String nomeTutor,
        String telefone,
        String nif,
        String nomeAnimal,
        String especie,
        String raca,
        Integer anoNascimento,
        String microchip
) {
}
