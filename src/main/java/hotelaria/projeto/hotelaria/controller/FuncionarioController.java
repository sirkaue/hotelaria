package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.RegistrarCheckInRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarCheckOutRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarConsumoRequestDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/{funcionarioId}/checkin")
    public ResponseEntity<Void> registrarCheckIn(
            @PathVariable Long funcionarioId,
            @RequestBody RegistrarCheckInRequestDTO dto
    ) {
        funcionarioService.registrarCheckIn(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{funcionarioId}/checkout")
    public ResponseEntity<Void> registrarCheckOut(
            @PathVariable Long funcionarioId,
            @RequestBody RegistrarCheckOutRequestDTO dto
    ) {
        funcionarioService.registrarCheckOut(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{funcionarioId}/consumo")
    public ResponseEntity<Void> registrarConsumo(
            @PathVariable Long funcionarioId,
            @RequestBody RegistrarConsumoRequestDTO dto
    ) {
        funcionarioService.registrarConsumo(funcionarioId, dto);
        return ResponseEntity.ok().build();
    }
}
