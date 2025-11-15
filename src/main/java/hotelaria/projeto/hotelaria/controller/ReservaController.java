package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.ReservaAlterarDatasRequestDTO;
import hotelaria.projeto.hotelaria.mappers.ReservaMapper;
import hotelaria.projeto.hotelaria.service.ReservaService;
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

    @PostMapping("/{reservaId}/confirmar")
    public ResponseEntity<Void> confirmarReserva(@PathVariable Long reservaId) {
        reservaService.confirmarReserva(reservaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reservaId}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelarReserva(reservaId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{reservaId}/datas")
    public ResponseEntity<Void> alterarDatas(
            @PathVariable Long reservaId,
            @RequestBody ReservaAlterarDatasRequestDTO dto
    ) {
        LocalDateTime[] datas = ReservaMapper.toDatas(dto);
        reservaService.alterarDatas(reservaId, datas[0], datas[1]);
        return ResponseEntity.ok().build();
    }
}
