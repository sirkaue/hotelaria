package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.ServicoExtraAtualizarPrecoRequestDTO;
import hotelaria.projeto.hotelaria.mappers.ServicoExtraMapper;
import hotelaria.projeto.hotelaria.service.ServicoExtraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos-extras")
public class ServicoExtraController {

    private final ServicoExtraService servicoExtraService;

    public ServicoExtraController(ServicoExtraService servicoExtraService) {
        this.servicoExtraService = servicoExtraService;
    }

    @PutMapping("/{servicoId}/preco")
    public ResponseEntity<Void> atualizarPreco(
            @PathVariable Long servicoId,
            @RequestBody ServicoExtraAtualizarPrecoRequestDTO dto
    ) {
        double novoPreco = ServicoExtraMapper.toNovoPreco(dto);
        servicoExtraService.atualizarPreco(servicoId, novoPreco);
        return ResponseEntity.ok().build();
    }
}
