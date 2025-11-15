package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.ServicoExtraAtualizarPrecoRequestDTO;
import hotelaria.projeto.hotelaria.mappers.ServicoExtraMapper;
import hotelaria.projeto.hotelaria.service.ServicoExtraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos-extras")
public class ServicoExtraController {

    private final ServicoExtraService servicoExtraService;

    public ServicoExtraController(ServicoExtraService servicoExtraService) {
        this.servicoExtraService = servicoExtraService;
    }

    @Operation(summary = "Atualiza o preço de um serviço extra",
            description = "Atualiza o valor cobrado de um serviço extra existente, identificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço extra não encontrado")
    })
    @PutMapping("/{servicoId}/preco")
    public ResponseEntity<Void> atualizarPreco(
            @Parameter(description = "ID do serviço extra a ter o preço atualizado", required = true)
            @PathVariable Long servicoId,
            @Parameter(description = "Novo preço a ser aplicado", required = true)
            @RequestBody ServicoExtraAtualizarPrecoRequestDTO dto
    ) {
        double novoPreco = ServicoExtraMapper.toNovoPreco(dto);
        servicoExtraService.atualizarPreco(servicoId, novoPreco);
        return ResponseEntity.ok().build();
    }
}
