package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.RegistrarCheckInRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarCheckOutRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarConsumoRequestDTO;
import hotelaria.projeto.hotelaria.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Registra check-in de um hóspede",
            description = "Permite que um funcionário registre o check-in de um hóspede em uma estadia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check-in registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia ou hóspede não encontrados")
    })
    @PostMapping("/{funcionarioId}/checkin")
    public ResponseEntity<Void> registrarCheckIn(
            @Parameter(description = "ID do funcionário que registra o check-in", required = true)
            @PathVariable Long funcionarioId,
            @Parameter(description = "Dados do check-in a ser registrado", required = true)
            @RequestBody RegistrarCheckInRequestDTO dto
    ) {
        funcionarioService.registrarCheckIn(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Registra check-out de um hóspede",
            description = "Permite que um funcionário registre o check-out de um hóspede em uma estadia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Check-out registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia não encontrada")
    })
    @PostMapping("/{funcionarioId}/checkout")
    public ResponseEntity<Void> registrarCheckOut(
            @Parameter(description = "ID do funcionário que registra o check-out", required = true)
            @PathVariable Long funcionarioId,
            @Parameter(description = "Dados do check-out a ser registrado", required = true)
            @RequestBody RegistrarCheckOutRequestDTO dto
    ) {
        funcionarioService.registrarCheckOut(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Registra consumo de serviço extra",
            description = "Permite que um funcionário adicione um consumo (serviço extra) à estadia de um hóspede.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consumo registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia ou serviço extra não encontrados")
    })
    @PostMapping("/{funcionarioId}/consumo")
    public ResponseEntity<Void> registrarConsumo(
            @Parameter(description = "ID do funcionário que registra o consumo", required = true)
            @PathVariable Long funcionarioId,
            @Parameter(description = "Dados do consumo a ser registrado", required = true)
            @RequestBody RegistrarConsumoRequestDTO dto
    ) {
        funcionarioService.registrarConsumo(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }
}
