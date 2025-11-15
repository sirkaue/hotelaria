package hotelaria.projeto.hotelaria.dtos;

import java.time.LocalDateTime;

public record ItemConsumidoResponseDTO(
        Long id,
        int quantidade,
        LocalDateTime dataConsumo,
        double valorCobrado,
        Long servicoExtraId
) {}

