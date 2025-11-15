package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registrar o check-in de um hóspede")
public record RegistrarCheckInRequestDTO(
        @Schema(description = "ID da estadia", example = "12", required = true)
        Long estadiaId,

        @Schema(description = "ID do quarto", example = "5", required = true)
        Long quartoId,

        @Schema(description = "ID do hóspede", example = "3", required = true)
        Long hospedeId
) {}
