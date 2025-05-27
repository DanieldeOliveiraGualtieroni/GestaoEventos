package atividade.gestao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import atividade.gestao.dto.ParticipanteDTO;
import atividade.gestao.entities.Participante;
import atividade.gestao.services.ParticipanteService;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    // POST /participantes
    @PostMapping
    public ResponseEntity<ParticipanteDTO> cadastrarParticipante(@RequestBody ParticipanteDTO participanteDTO) {
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
    @PostMapping("/{participanteId}/eventos/{eventoId}")
    public ResponseEntity<String> inscreverEmEvento(@PathVariable Long participanteId, @PathVariable Long eventoId) {
        participanteService.inscreverParticipanteEmEvento(participanteId, eventoId);
        return ResponseEntity.ok("Participante inscrito com sucesso no evento");
    }

    // DELETE /participantes/{participanteId}/eventos/{eventoId}
    @DeleteMapping("/{participanteId}/eventos/{eventoId}")
    public ResponseEntity<String> cancelarInscricao(@PathVariable Long participanteId, @PathVariable Long eventoId) {
        participanteService.cancelarInscricao(participanteId, eventoId);
        return ResponseEntity.ok("Inscrição cancelada com sucesso");
    }
}
