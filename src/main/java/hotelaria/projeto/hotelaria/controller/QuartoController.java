package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.QuartoEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.mappers.QuartoMapper;
import hotelaria.projeto.hotelaria.service.QuartoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping("/{quartoId}/preco-base")
    public ResponseEntity<Double> calcularPrecoBase(@PathVariable Long quartoId) {
        return ResponseEntity.ok(quartoService.calcularPrecoBase(quartoId));
    }

    @GetMapping("/{quartoId}/premium")
    public ResponseEntity<Boolean> permitePremium(@PathVariable Long quartoId) {
        return ResponseEntity.ok(quartoService.permiteServicosPremium(quartoId));
    }

    @PostMapping("/{quartoId}/estadias")
    public ResponseEntity<Void> adicionarEstadia(
            @PathVariable Long quartoId,
            @RequestBody QuartoEstadiaRequestDTO dto
    ) {
        quartoService.adicionarEstadia(quartoId, QuartoMapper.toEstadia(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{quartoId}/estadias")
    public ResponseEntity<Void> removerEstadia(
            @PathVariable Long quartoId,
            @RequestBody QuartoEstadiaRequestDTO dto
    ) {
        quartoService.removerEstadia(quartoId, QuartoMapper.toEstadia(dto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{quartoId}/disponivel")
    public ResponseEntity<Boolean> verificarDisponibilidade(@PathVariable Long quartoId) {
        return ResponseEntity.ok(quartoService.verificarDisponibilidade(quartoId));
    }

    @PostMapping("/{quartoId}/limpo")
    public ResponseEntity<Void> marcarComoLimpo(@PathVariable Long quartoId) {
        quartoService.marcarComoLimpo(quartoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{quartoId}/ocupado")
    public ResponseEntity<Void> marcarComoOcupado(@PathVariable Long quartoId) {
        quartoService.marcarComoOcupado(quartoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{quartoId}/limpeza")
    public ResponseEntity<Void> marcarParaLimpeza(@PathVariable Long quartoId) {
        quartoService.marcarParaLimpeza(quartoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{quartoId}/manutencao")
    public ResponseEntity<Void> marcarParaManutencao(@PathVariable Long quartoId) {
        quartoService.marcarParaManutencao(quartoId);
        return ResponseEntity.ok().build();
    }
}
