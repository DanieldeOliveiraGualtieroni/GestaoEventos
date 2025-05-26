package atividade.gestao.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import atividade.gestao.entities.Evento;
import atividade.gestao.entities.Participante;
import atividade.gestao.repositories.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

  
    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

  
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

 
    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        return eventoRepository.findById(id)
                .map(evento -> {
                    evento.setNome(eventoAtualizado.getNome());
                    evento.setDescricao(eventoAtualizado.getDescricao());
                    evento.setData(eventoAtualizado.getData());
                    evento.setLocal(eventoAtualizado.getLocal());
                    evento.setVagas(eventoAtualizado.getVagas());
                    return eventoRepository.save(evento);
                })
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }


    public void excluirEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        eventoRepository.deleteById(id);
    }

   
    public Set<Participante> listarParticipantesDoEvento(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

       
        return evento.getParticipantes();
    }
}

