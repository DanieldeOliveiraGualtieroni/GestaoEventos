package atividade.gestao.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atividade.gestao.dto.EventoDTO;
import atividade.gestao.entities.Evento;
import atividade.gestao.entities.Participante;
import atividade.gestao.services.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // GET /eventos
    @Operation(summary = "Lista todos os eventos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eventos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<EventoDTO>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        List<EventoDTO> eventosDTO = eventos.stream()
                                            .map(EventoDTO::new)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(eventosDTO);
    }

    // POST /eventos
    @Operation(summary = "Cria um novo evento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<EventoDTO> criarEvento(
        @RequestBody @Parameter(description = "Dados do novo evento") EventoDTO eventoDTO) {

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
    @Operation(summary = "Atualiza um evento existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> atualizarEvento(
        @PathVariable @Parameter(description = "ID do evento a ser atualizado") Long id,
        @RequestBody @Parameter(description = "Novos dados do evento") EventoDTO eventoDTO) {

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
    @Operation(summary = "Exclui um evento por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Evento excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEvento(
        @PathVariable @Parameter(description = "ID do evento a ser excluído") Long id) {

        eventoService.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }

    // GET /eventos/{id}/participantes
    @Operation(summary = "Lista os participantes de um evento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Participantes listados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}/participantes")
    public ResponseEntity<Set<Participante>> listarParticipantesDoEvento(
        @PathVariable @Parameter(description = "ID do evento") Long id) {

        Set<Participante> participantes = eventoService.listarParticipantesDoEvento(id);
        return ResponseEntity.ok(participantes);
    }
}
