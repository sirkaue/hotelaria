package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para adicionar uma estadia a um hóspede")
public record HospedeAdicionarEstadiaRequestDTO(
        @Schema(description = "ID da estadia a ser adicionada", example = "12", required = true)
        Long estadiaId
) {}
