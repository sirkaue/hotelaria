package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registrar um consumo de serviço extra")
public record RegistrarConsumoRequestDTO(
        @Schema(description = "ID da estadia", example = "12", required = true)
        Long estadiaId,

        @Schema(description = "ID do serviço extra consumido", example = "4", required = true)
        Long servicoExtraId,

        @Schema(description = "Quantidade consumida", example = "2", required = true)
        int quantidade
) {}
