package hotelaria.projeto.hotelaria.controller;

import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarReservaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeCreateRequestDTO;
import hotelaria.projeto.hotelaria.service.HospedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @Operation(
            summary = "Criar um novo hóspede",
            description = "Endpoint para cadastrar hóspedes no sistema. "
                    + "Os dados serão usados para reservas e estadias."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hóspede criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Void> criarHospede(
            @RequestBody HospedeCreateRequestDTO dto
    ) {
        hospedeService.criar(dto);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Adiciona uma reserva ao hóspede",
            description = "Permite adicionar uma reserva existente ao perfil de um hóspede.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede ou reserva não encontrados")
    })
    @PostMapping("/{hospedeId}/reservas")
    public ResponseEntity<Void> adicionarReserva(
            @Parameter(description = "ID do hóspede", required = true) @PathVariable Long hospedeId,
            @Parameter(description = "Dados da reserva a ser adicionada", required = true)
            @RequestBody HospedeAdicionarReservaRequestDTO dto
    ) {
        hospedeService.adicionarReserva(hospedeId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove uma reserva do hóspede",
            description = "Permite remover uma reserva associada a um hóspede.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede ou reserva não encontrados")
    })
    @DeleteMapping("/{hospedeId}/reservas/{reservaId}")
    public ResponseEntity<Void> removerReserva(
            @Parameter(description = "ID do hóspede", required = true) @PathVariable Long hospedeId,
            @Parameter(description = "ID da reserva a ser removida", required = true) @PathVariable Long reservaId
    ) {
        hospedeService.removerReserva(hospedeId, reservaId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Adiciona uma estadia ao hóspede",
            description = "Permite adicionar uma estadia existente ao perfil de um hóspede.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadia adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hóspede ou estadia não encontrados")
    })
    @PostMapping("/{hospedeId}/estadias")
    public ResponseEntity<Void> adicionarEstadia(
            @Parameter(description = "ID do hóspede", required = true) @PathVariable Long hospedeId,
            @Parameter(description = "Dados da estadia a ser adicionada", required = true)
            @RequestBody HospedeAdicionarEstadiaRequestDTO dto
    ) {
        hospedeService.adicionarEstadia(hospedeId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove uma estadia",
            description = "Permite remover uma estadia pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadia removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estadia não encontrada")
    })
    @DeleteMapping("/estadias/{estadiaId}")
    public ResponseEntity<Void> removerEstadia(
            @Parameter(description = "ID da estadia a ser removida", required = true) @PathVariable Long estadiaId
    ) {
        hospedeService.removerEstadia(estadiaId);
        return ResponseEntity.ok().build();
    }
}
