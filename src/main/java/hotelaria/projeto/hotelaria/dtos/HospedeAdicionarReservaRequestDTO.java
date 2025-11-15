package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para adicionar uma reserva a um hóspede")
public record HospedeAdicionarReservaRequestDTO(
        @Schema(description = "ID da reserva a ser adicionada", example = "7", required = true)
        Long reservaId
) {}
