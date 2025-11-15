package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarReservaRequestDTO;
import hotelaria.projeto.hotelaria.service.HospedeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping("/{hospedeId}/reservas")
    public ResponseEntity<Void> adicionarReserva(
            @PathVariable Long hospedeId,
            @RequestBody HospedeAdicionarReservaRequestDTO dto
    ) {
        hospedeService.adicionarReserva(hospedeId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hospedeId}/reservas/{reservaId}")
    public ResponseEntity<Void> removerReserva(
            @PathVariable Long hospedeId,
            @PathVariable Long reservaId
    ) {
        hospedeService.removerReserva(hospedeId, reservaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{hospedeId}/estadias")
    public ResponseEntity<Void> adicionarEstadia(
            @PathVariable Long hospedeId,
            @RequestBody HospedeAdicionarEstadiaRequestDTO dto
    ) {
        hospedeService.adicionarEstadia(hospedeId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/estadias/{estadiaId}")
    public ResponseEntity<Void> removerEstadia(@PathVariable Long estadiaId) {
        hospedeService.removerEstadia(estadiaId);
        return ResponseEntity.ok().build();
    }
}
