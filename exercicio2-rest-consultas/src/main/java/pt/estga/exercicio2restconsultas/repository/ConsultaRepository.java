package pt.estga.exercicio2restconsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.estga.exercicio2restconsultas.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}