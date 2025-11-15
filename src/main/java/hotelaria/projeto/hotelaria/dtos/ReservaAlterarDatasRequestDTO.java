package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Dados para alterar as datas de uma reserva")
public record ReservaAlterarDatasRequestDTO(
        @Schema(description = "Nova data de início da reserva", example = "2025-12-01T14:00:00", required = true)
        LocalDateTime dataInicio,

        @Schema(description = "Nova data de término da reserva", example = "2025-12-05T12:00:00", required = true)
        LocalDateTime dataFim
) {}
