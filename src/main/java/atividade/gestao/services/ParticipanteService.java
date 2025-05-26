package atividade.gestao.services;

import org.springframework.stereotype.Service;

import atividade.gestao.entities.Evento;
import atividade.gestao.entities.Participante;
import atividade.gestao.repositories.EventoRepository;
import atividade.gestao.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final EventoRepository eventoRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository, EventoRepository eventoRepository) {
        this.participanteRepository = participanteRepository;
        this.eventoRepository = eventoRepository;
    }

    public Participante cadastrarParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void inscreverParticipanteEmEvento(Long participanteId, Long eventoId) {
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (evento.getParticipantes().size() >= evento.getVagas()) {
            throw new RuntimeException("Não há vagas disponíveis para esse evento");
        }

 
        if (evento.getParticipantes().contains(participante)) {
            throw new RuntimeException("Participante já inscrito neste evento");
        }

        evento.getParticipantes().add(participante);
        eventoRepository.save(evento);
    }

    public void cancelarInscricao(Long participanteId, Long eventoId) {
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (!evento.getParticipantes().contains(participante)) {
            throw new RuntimeException("Participante não inscrito neste evento");
        }

        evento.getParticipantes().remove(participante);
        eventoRepository.save(evento);
    }
}

