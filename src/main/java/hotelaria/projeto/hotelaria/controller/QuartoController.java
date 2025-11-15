package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.QuartoEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.mappers.QuartoMapper;
import hotelaria.projeto.hotelaria.service.QuartoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @Operation(summary = "Calcula o preço base de um quarto",
            description = "Retorna o preço base do quarto identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço base calculado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @GetMapping("/{quartoId}/preco-base")
    public ResponseEntity<Double> calcularPrecoBase(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        return ResponseEntity.ok(quartoService.calcularPrecoBase(quartoId));
    }

    @Operation(summary = "Verifica se o quarto permite serviços premium",
            description = "Retorna true se o quarto permite serviços premium.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verificação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @GetMapping("/{quartoId}/premium")
    public ResponseEntity<Boolean> permitePremium(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        return ResponseEntity.ok(quartoService.permiteServicosPremium(quartoId));
    }

    @Operation(summary = "Adiciona uma estadia ao quarto",
            description = "Associa uma estadia ao quarto identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadia adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto ou estadia não encontrados")
    })
    @PostMapping("/{quartoId}/estadias")
    public ResponseEntity<Void> adicionarEstadia(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId,
            @Parameter(description = "Dados da estadia a ser adicionada", required = true)
            @RequestBody QuartoEstadiaRequestDTO dto
    ) {
        quartoService.adicionarEstadia(quartoId, QuartoMapper.toEstadia(dto));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove uma estadia do quarto",
            description = "Remove a associação de uma estadia do quarto identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadia removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto ou estadia não encontrados")
    })
    @DeleteMapping("/{quartoId}/estadias")
    public ResponseEntity<Void> removerEstadia(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId,
            @Parameter(description = "Dados da estadia a ser removida", required = true)
            @RequestBody QuartoEstadiaRequestDTO dto
    ) {
        quartoService.removerEstadia(quartoId, QuartoMapper.toEstadia(dto));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Verifica se o quarto está disponível",
            description = "Retorna true se o quarto estiver disponível para ocupação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidade verificada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @GetMapping("/{quartoId}/disponivel")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        return ResponseEntity.ok(quartoService.verificarDisponibilidade(quartoId));
    }

    @Operation(summary = "Marca o quarto como limpo",
            description = "Atualiza o status do quarto para limpo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto marcado como limpo"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @PostMapping("/{quartoId}/limpo")
    public ResponseEntity<Void> marcarComoLimpo(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        quartoService.marcarComoLimpo(quartoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Marca o quarto como ocupado",
            description = "Atualiza o status do quarto para ocupado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto marcado como ocupado"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @PostMapping("/{quartoId}/ocupado")
    public ResponseEntity<Void> marcarComoOcupado(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        quartoService.marcarComoOcupado(quartoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Marca o quarto para limpeza",
            description = "Atualiza o status do quarto para precisar de limpeza.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto marcado para limpeza"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @PostMapping("/{quartoId}/limpeza")
    public ResponseEntity<Void> marcarParaLimpeza(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        quartoService.marcarParaLimpeza(quartoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Marca o quarto para manutenção",
            description = "Atualiza o status do quarto para precisar de manutenção.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quarto marcado para manutenção"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
    })
    @PostMapping("/{quartoId}/manutencao")
    public ResponseEntity<Void> marcarParaManutencao(
            @Parameter(description = "ID do quarto", required = true) @PathVariable Long quartoId
    ) {
        quartoService.marcarParaManutencao(quartoId);
        return ResponseEntity.ok().build();
    }
}
