package atividade.gestao.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import atividade.gestao.dto.EventoDTO;
import atividade.gestao.entities.Evento;
import atividade.gestao.entities.Participante;
import atividade.gestao.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // GET /eventos
    @GetMapping
    public ResponseEntity<List<EventoDTO>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventosDTO);
    }

    // POST /eventos
    @PostMapping
    public ResponseEntity<EventoDTO> criarEvento(@RequestBody EventoDTO eventoDTO) {
        Evento evento = new Evento(
                null,
                eventoDTO.getNome(),
                eventoDTO.getDescricao(),
                eventoDTO.getData(),
                eventoDTO.getLocal(),
                eventoDTO.getVagas()
        );
        Evento novoEvento = eventoService.criarEvento(evento);
        return ResponseEntity.ok(new EventoDTO(novoEvento));
    }

    // PUT /eventos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> atualizarEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        Evento eventoAtualizado = new Evento(
                id,
                eventoDTO.getNome(),
                eventoDTO.getDescricao(),
                eventoDTO.getData(),
                eventoDTO.getLocal(),
                eventoDTO.getVagas()
        );
        Evento eventoSalvo = eventoService.atualizarEvento(id, eventoAtualizado);
        return ResponseEntity.ok(new EventoDTO(eventoSalvo));
    }

    // DELETE /eventos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
        eventoService.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }

    // GET /eventos/{id}/participantes
    @GetMapping("/{id}/participantes")
    public ResponseEntity<Set<Participante>> listarParticipantesDoEvento(@PathVariable Long id) {
        Set<Participante> participantes = eventoService.listarParticipantesDoEvento(id);
        return ResponseEntity.ok(participantes);
    }
}

