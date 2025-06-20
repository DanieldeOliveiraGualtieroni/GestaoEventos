package atividade.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atividade.gestao.entities.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long>{

}
