package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.AtribuirFuncionarioRequestDTO;
import hotelaria.projeto.hotelaria.mappers.TarefaLimpezaMapper;
import hotelaria.projeto.hotelaria.service.TarefaLimpezaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas-limpeza")
public class TarefaLimpezaController {

    private final TarefaLimpezaService tarefaLimpezaService;

    public TarefaLimpezaController(TarefaLimpezaService tarefaLimpezaService) {
        this.tarefaLimpezaService = tarefaLimpezaService;
    }

    @Operation(summary = "Inicia uma tarefa de limpeza",
            description = "Marca a tarefa de limpeza como iniciada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa iniciada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PostMapping("/{tarefaId}/iniciar")
    public ResponseEntity<Void> iniciarTarefa(
            @Parameter(description = "ID da tarefa de limpeza", required = true) @PathVariable Long tarefaId
    ) {
        tarefaLimpezaService.iniciarTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Conclui uma tarefa de limpeza",
            description = "Marca a tarefa de limpeza como concluída.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa concluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PostMapping("/{tarefaId}/concluir")
    public ResponseEntity<Void> concluirTarefa(
            @Parameter(description = "ID da tarefa de limpeza", required = true) @PathVariable Long tarefaId
    ) {
        tarefaLimpezaService.concluirTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atribui um funcionário a uma tarefa de limpeza",
            description = "Associa um funcionário à tarefa de limpeza.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atribuído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa ou funcionário não encontrados")
    })
    @PostMapping("/{tarefaId}/atribuir")
    public ResponseEntity<Void> atribuirFuncionario(
            @Parameter(description = "ID da tarefa de limpeza", required = true) @PathVariable Long tarefaId,
            @Parameter(description = "Dados do funcionário a ser atribuído", required = true)
            @RequestBody AtribuirFuncionarioRequestDTO dto
    ) {
        tarefaLimpezaService.atribuirFuncionario(tarefaId, TarefaLimpezaMapper.toFuncionario(dto));
        return ResponseEntity.ok().build();
    }
}
