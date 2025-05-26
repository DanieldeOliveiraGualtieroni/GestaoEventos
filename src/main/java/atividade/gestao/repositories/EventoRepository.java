package atividade.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atividade.gestao.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
