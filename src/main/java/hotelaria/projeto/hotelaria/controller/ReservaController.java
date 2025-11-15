package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.ReservaAlterarDatasRequestDTO;
import hotelaria.projeto.hotelaria.mappers.ReservaMapper;
import hotelaria.projeto.hotelaria.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Operation(summary = "Confirma uma reserva",
            description = "Confirma a reserva identificada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva confirmada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PostMapping("/{reservaId}/confirmar")
    public ResponseEntity<Void> confirmarReserva(
            @Parameter(description = "ID da reserva a ser confirmada", required = true)
            @PathVariable Long reservaId
    ) {
        reservaService.confirmarReserva(reservaId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Cancela uma reserva",
            description = "Cancela a reserva identificada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva cancelada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PostMapping("/{reservaId}/cancelar")
    public ResponseEntity<Void> cancelarReserva(
            @Parameter(description = "ID da reserva a ser cancelada", required = true)
            @PathVariable Long reservaId
    ) {
        reservaService.cancelarReserva(reservaId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Altera datas de uma reserva",
            description = "Atualiza as datas de início e fim da reserva identificada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datas alteradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
    })
    @PutMapping("/{reservaId}/datas")
    public ResponseEntity<Void> alterarDatas(
            @Parameter(description = "ID da reserva a ser alterada", required = true)
            @PathVariable Long reservaId,
            @Parameter(description = "Novas datas para a reserva", required = true)
            @RequestBody ReservaAlterarDatasRequestDTO dto
    ) {
        LocalDateTime[] datas = ReservaMapper.toDatas(dto);
        reservaService.alterarDatas(reservaId, datas[0], datas[1]);
        return ResponseEntity.ok().build();
    }
}
