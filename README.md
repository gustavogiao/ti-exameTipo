# VetSaude Exame Tipo

Projetos do exame-tipo para gestao de consultas veterinarias.

## Projetos

- `exercicio1-soap-tutores`: SOAP Spring para consultar tutor por microchip, porta `8002`.
- `exercicio2-rest-consultas`: REST JAX-RS/Jersey para agendamento de consultas, porta `8001`.
- `exercicio3-soap-agendamento-final`: SOAP mediador para agendamento final, porta `8000`.

## Exercicio 1

```bash
cd exercicio1-soap-tutores
mvn spring-boot:run
```

WSDL:

```text
http://localhost:8002/services/tutors.wsdl
```

Microchips de teste:

```text
123456789012345
987654321098765
111222333444555
```

## Exercicio 2

```bash
cd exercicio2-rest-consultas
mvn spring-boot:run
```

Endpoint:

```text
POST http://localhost:8001/api/consultas
```
