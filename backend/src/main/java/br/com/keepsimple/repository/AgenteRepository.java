package br.com.keepsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.keepsimple.model.Agente;

public interface AgenteRepository extends JpaRepository<Agente, Long> {

}
