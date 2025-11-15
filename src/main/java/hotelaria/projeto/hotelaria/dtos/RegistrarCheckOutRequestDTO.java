package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registrar o check-out de um hóspede")
public record RegistrarCheckOutRequestDTO(
        @Schema(description = "ID da estadia", example = "12", required = true)
        Long estadiaId
) {}
