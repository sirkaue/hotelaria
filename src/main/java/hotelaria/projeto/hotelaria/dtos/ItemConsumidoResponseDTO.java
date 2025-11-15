package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Informações de um item consumido na estadia")
public record ItemConsumidoResponseDTO(
        @Schema(description = "ID do item consumido", example = "5")
        Long id,

        @Schema(description = "Quantidade consumida do item", example = "2")
        int quantidade,

        @Schema(description = "Data e hora em que o consumo foi registrado", example = "2025-11-15T14:30:00")
        LocalDateTime dataConsumo,

        @Schema(description = "Valor cobrado pelo item consumido", example = "49.90")
        double valorCobrado,

        @Schema(description = "ID do serviço extra associado, se houver", example = "3")
        Long servicoExtraId
) {
}

