package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.EstadiaConsumoRequestDTO;
import hotelaria.projeto.hotelaria.dtos.EstadiaResponseDTO;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.service.EstadiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadias")
public class EstadiaController {

    private final EstadiaService estadiaService;

    public EstadiaController(EstadiaService estadiaService) {
        this.estadiaService = estadiaService;
    }

    @PostMapping("/{id}/consumos")
    public ResponseEntity<EstadiaResponseDTO> adicionarConsumo(
            @PathVariable Long id,
            @RequestBody EstadiaConsumoRequestDTO dto
    ) {
        EstadiaResponseDTO response = estadiaService.adicionarConsumo(id, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/calcular-total")
    public ResponseEntity<EstadiaResponseDTO> calcularTotal(@PathVariable Long id) {
        EstadiaResponseDTO response = estadiaService.calcularValorTotal(id);
        return ResponseEntity.ok(response);
    }
}

