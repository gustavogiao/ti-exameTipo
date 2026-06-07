# VetSaude Exame Tipo

Projeto de exame-tipo para uma clinica veterinaria, composto por tres servicos independentes que comunicam entre si.

## Projetos

| Projeto | Tipo | Porta | Descricao |
| --- | --- | --- | --- |
| `exercicio1-soap-tutores` | SOAP Spring Web Services | `8002` | Consulta dados do tutor e animal por microchip. |
| `exercicio2-rest-consultas` | REST JAX-RS/Jersey + Gson | `8001` | Agenda consultas veterinarias via JSON. |
| `exercicio3-soap-agendamento-final` | SOAP JAX-WS | `8000` | Mediador que integra o SOAP do exercicio 1 com o REST do exercicio 2. |

## Requisitos

- Java `17+`
- Maven `3.8+`
- Portas livres: `8000`, `8001`, `8002`
- Postman, SoapUI ou curl para testes manuais

Confirmar versoes:

```bash
java -version
mvn -version
```

## Como Correr Tudo

Abrir tres terminais separados na raiz do repositorio.

Terminal 1:

```bash
cd exercicio1-soap-tutores
mvn spring-boot:run
```

Terminal 2:

```bash
cd exercicio2-rest-consultas
mvn spring-boot:run
```

Terminal 3:

```bash
cd exercicio3-soap-agendamento-final
mvn spring-boot:run
```

Ordem recomendada:

```text
1. exercicio1-soap-tutores
2. exercicio2-rest-consultas
3. exercicio3-soap-agendamento-final
```

## Build

Compilar cada projeto:

```bash
cd exercicio1-soap-tutores
mvn test
```

```bash
cd exercicio2-rest-consultas
mvn test
```

```bash
cd exercicio3-soap-agendamento-final
mvn test
```

Gerar `.jar`:

```bash
mvn -DskipTests package
```

Correr via `.jar`:

```bash
java -jar target/nome-do-ficheiro.jar
```

## Exercicio 1 - SOAP Tutores

Servico SOAP que recebe um microchip de 15 digitos e devolve dados do tutor e do animal.

WSDL:

```text
http://localhost:8002/services/tutors.wsdl
```

Endpoint SOAP:

```text
http://localhost:8002/services
```

Microchips de teste:

```text
123456789012345
987654321098765
111222333444555
```

### Pedido SOAP

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:tut="http://estga.pt/vetsaude/tutors">
   <soapenv:Header/>
   <soapenv:Body>
      <tut:getTutorByMicrochipRequest>
         <tut:microchip>123456789012345</tut:microchip>
      </tut:getTutorByMicrochipRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Testar Com curl

Guardar o XML anterior num ficheiro `request-ex1.xml` e executar:

```bash
curl -X POST http://localhost:8002/services \
  -H "Content-Type: text/xml;charset=UTF-8" \
  --data-binary @request-ex1.xml
```

### Testar No Postman

- Method: `POST`
- URL: `http://localhost:8002/services`
- Header: `Content-Type: text/xml;charset=UTF-8`
- Body: `raw` com o XML SOAP acima

## Exercicio 2 - REST Consultas

Servico REST com JAX-RS/Jersey e Gson para agendamento de consultas veterinarias.

Endpoint:

```text
POST http://localhost:8001/api/consultas
```

### Pedido JSON Valido

```json
{
  "animal": {
    "microchip": "123456789012345",
    "nome": "Luna",
    "especie": "Canideo",
    "raca": "Labrador"
  },
  "tutor": {
    "nome": "Maria Silva",
    "telefone": "912345678"
  },
  "agendamento": {
    "data": "2027-06-10",
    "hora": "14:30",
    "tipoConsulta": "Vacinacao"
  }
}
```

Resposta esperada:

```text
HTTP 201 Created
```

```json
{
  "mensagem": "Consulta agendada com sucesso.",
  "microchip": "123456789012345",
  "nomeAnimal": "Luna",
  "nomeTutor": "Maria Silva",
  "tipoConsulta": "Vacinacao",
  "data": "2027-06-10",
  "hora": "14:30"
}
```

### Testar Com curl

```bash
curl -X POST http://localhost:8001/api/consultas \
  -H "Content-Type: application/json" \
  -d "{\"animal\":{\"microchip\":\"123456789012345\",\"nome\":\"Luna\",\"especie\":\"Canideo\",\"raca\":\"Labrador\"},\"tutor\":{\"nome\":\"Maria Silva\",\"telefone\":\"912345678\"},\"agendamento\":{\"data\":\"2027-06-10\",\"hora\":\"14:30\",\"tipoConsulta\":\"Vacinacao\"}}"
```

### Testar No Postman

- Method: `POST`
- URL: `http://localhost:8001/api/consultas`
- Header: `Content-Type: application/json`
- Body: `raw` com tipo `JSON`
- Colar o JSON valido acima

### Exemplos De Erro

Microchip invalido:

```json
{
  "animal": {
    "microchip": "123",
    "nome": "Luna",
    "especie": "Canideo",
    "raca": "Labrador"
  },
  "tutor": {
    "nome": "Maria Silva",
    "telefone": "912345678"
  },
  "agendamento": {
    "data": "2027-06-10",
    "hora": "14:30",
    "tipoConsulta": "Vacinacao"
  }
}
```

Resposta esperada:

```text
HTTP 400 Bad Request
```

Data passada:

```json
{
  "animal": {
    "microchip": "123456789012345",
    "nome": "Luna",
    "especie": "Canideo",
    "raca": "Labrador"
  },
  "tutor": {
    "nome": "Maria Silva",
    "telefone": "912345678"
  },
  "agendamento": {
    "data": "2020-01-01",
    "hora": "14:30",
    "tipoConsulta": "Vacinacao"
  }
}
```

Resposta esperada:

```text
HTTP 400 Bad Request
```

## Exercicio 3 - SOAP Agendamento Final

Servico SOAP JAX-WS que atua como mediador.

Este servico:

1. Recebe `microchip`, `tipoConsulta`, `data` e `hora`.
2. Chama o SOAP do exercicio 1 para obter dados do tutor e animal.
3. Chama o REST do exercicio 2 para agendar a consulta.
4. Devolve uma mensagem final com os detalhes do agendamento.

WSDL:

```text
http://localhost:8000/services/agendamento?wsdl
```

Endpoint SOAP:

```text
http://localhost:8000/services/agendamento
```

### Pedido SOAP

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:soap="http://soap.exercicio3soapagendamentofinal.estga.pt/">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:agendarConsultaFinal>
         <microchip>123456789012345</microchip>
         <tipoConsulta>Vacinacao</tipoConsulta>
         <data>2027-06-10</data>
         <hora>14:30</hora>
      </soap:agendarConsultaFinal>
   </soapenv:Body>
</soapenv:Envelope>
```

Resposta esperada:

```xml
<return>
Consulta agendada com sucesso. Tutor: Maria Silva; Animal: Luna; Especie: Canideo; Raca: Labrador Retriever; Tipo de consulta: Vacinacao; Data: 2027-06-10; Hora: 14:30.
</return>
```

### Testar Com curl

Guardar o XML anterior num ficheiro `request-ex3.xml` e executar:

```bash
curl -X POST http://localhost:8000/services/agendamento \
  -H "Content-Type: text/xml;charset=UTF-8" \
  --data-binary @request-ex3.xml
```

### Testar No Postman

- Method: `POST`
- URL: `http://localhost:8000/services/agendamento`
- Header: `Content-Type: text/xml;charset=UTF-8`
- Body: `raw` com o XML SOAP do exercicio 3

## Teste Do Pipeline Completo

Com os tres servicos a correr, confirmar:

```text
http://localhost:8002/services/tutors.wsdl
http://localhost:8001/api/consultas
http://localhost:8000/services/agendamento?wsdl
```

Depois enviar o pedido SOAP para:

```text
http://localhost:8000/services/agendamento
```

O fluxo esperado e:

```text
Cliente SOAP -> Exercicio 3 SOAP -> Exercicio 1 SOAP -> Exercicio 3 SOAP -> Exercicio 2 REST -> Exercicio 3 SOAP -> Cliente SOAP
```

## H2 Console

Cada projeto usa H2 em memoria.

| Projeto | H2 Console | JDBC URL |
| --- | --- | --- |
| Exercicio 1 | `http://localhost:8002/h2-console` | `jdbc:h2:mem:vetsaude_tutores` |
| Exercicio 2 | `http://localhost:8001/h2-console` | `jdbc:h2:mem:vetsaude_consultas` |
| Exercicio 3 | Spring web desativado | `jdbc:h2:mem:vetsaude_agendamento_final` |

Credenciais:

```text
User: sa
Password: vazio
```

## Notas

- O exercicio 2 usa JAX-RS/Jersey para os endpoints REST, nao Spring MVC.
- O exercicio 3 usa JAX-WS para publicar o SOAP final.
- O exercicio 3 precisa que os exercicios 1 e 2 estejam ativos para concluir o pipeline completo.
- As bases H2 sao em memoria, por isso os dados desaparecem ao parar os servicos.
