package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.EstadiaConsumoRequestDTO;
import hotelaria.projeto.hotelaria.dtos.EstadiaResponseDTO;
import hotelaria.projeto.hotelaria.service.EstadiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadias")
public class EstadiaController {

    private final EstadiaService estadiaService;

    public EstadiaController(EstadiaService estadiaService) {
        this.estadiaService = estadiaService;
    }

    @Operation(summary = "Adiciona um consumo à estadia",
            description = "Registra um item consumido (como bebida ou serviço) na estadia identificada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consumo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia não encontrada")
    })
    @PostMapping("/{id}/consumos")
    public ResponseEntity<EstadiaResponseDTO> adicionarConsumo(
            @Parameter(description = "ID da estadia", required = true) @PathVariable Long id,
            @Parameter(description = "Dados do consumo a ser adicionado", required = true)
            @RequestBody EstadiaConsumoRequestDTO dto
    ) {
        EstadiaResponseDTO response = estadiaService.adicionarConsumo(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Calcula o valor total da estadia",
            description = "Retorna o valor total da estadia, incluindo todos os consumos adicionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia não encontrada")
    })
    @PostMapping("/{id}/calcular-total")
    public ResponseEntity<EstadiaResponseDTO> calcularTotal(
            @Parameter(description = "ID da estadia", required = true) @PathVariable Long id
    ) {
        EstadiaResponseDTO response = estadiaService.calcularValorTotal(id);
        return ResponseEntity.ok(response);
    }
}
