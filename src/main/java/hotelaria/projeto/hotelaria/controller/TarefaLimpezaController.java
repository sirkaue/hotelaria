package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.AtribuirFuncionarioRequestDTO;
import hotelaria.projeto.hotelaria.mappers.TarefaLimpezaMapper;
import hotelaria.projeto.hotelaria.service.TarefaLimpezaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas-limpeza")
public class TarefaLimpezaController {

    private final TarefaLimpezaService tarefaLimpezaService;

    public TarefaLimpezaController(TarefaLimpezaService tarefaLimpezaService) {
        this.tarefaLimpezaService = tarefaLimpezaService;
    }

    @PostMapping("/{tarefaId}/iniciar")
    public ResponseEntity<Void> iniciarTarefa(@PathVariable Long tarefaId) {
        tarefaLimpezaService.iniciarTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tarefaId}/concluir")
    public ResponseEntity<Void> concluirTarefa(@PathVariable Long tarefaId) {
        tarefaLimpezaService.concluirTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tarefaId}/atribuir")
    public ResponseEntity<Void> atribuirFuncionario(
            @PathVariable Long tarefaId,
            @RequestBody AtribuirFuncionarioRequestDTO dto
    ) {
        tarefaLimpezaService.atribuirFuncionario(tarefaId, TarefaLimpezaMapper.toFuncionario(dto));
        return ResponseEntity.ok().build();
    }
}
