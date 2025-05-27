package atividade.gestao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import atividade.gestao.dto.ParticipanteDTO;
import atividade.gestao.entities.Participante;
import atividade.gestao.services.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    // POST /participantes
    @Operation(summary = "Cadastra um novo participante")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Participante cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ParticipanteDTO> cadastrarParticipante(
        @RequestBody @Parameter(description = "Dados do participante") ParticipanteDTO participanteDTO) {

        Participante participante = new Participante(
            null,
            participanteDTO.getNome(),
            participanteDTO.getEmail(),
            participanteDTO.getTelefone()
        );
        Participante novoParticipante = participanteService.cadastrarParticipante(participante);
        return ResponseEntity.ok(new ParticipanteDTO(novoParticipante));
    }

    // POST /participantes/{participanteId}/eventos/{eventoId}
    @Operation(summary = "Inscreve um participante em um evento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inscrição realizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Participante ou evento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{participanteId}/eventos/{eventoId}")
    public ResponseEntity<String> inscreverEmEvento(
        @PathVariable @Parameter(description = "ID do participante") Long participanteId,
        @PathVariable @Parameter(description = "ID do evento") Long eventoId) {

        participanteService.inscreverParticipanteEmEvento(participanteId, eventoId);
        return ResponseEntity.ok("Participante inscrito com sucesso no evento");
    }

    // DELETE /participantes/{participanteId}/eventos/{eventoId}
    @Operation(summary = "Cancela a inscrição de um participante em um evento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inscrição cancelada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Participante ou evento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{participanteId}/eventos/{eventoId}")
    public ResponseEntity<String> cancelarInscricao(
        @PathVariable @Parameter(description = "ID do participante") Long participanteId,
        @PathVariable @Parameter(description = "ID do evento") Long eventoId) {

        participanteService.cancelarInscricao(participanteId, eventoId);
        return ResponseEntity.ok("Inscrição cancelada com sucesso");
    }
}
