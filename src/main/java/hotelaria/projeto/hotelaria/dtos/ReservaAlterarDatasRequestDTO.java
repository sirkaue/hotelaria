package hotelaria.projeto.hotelaria.dtos;

import java.time.LocalDateTime;

public record ReservaAlterarDatasRequestDTO(
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
}

