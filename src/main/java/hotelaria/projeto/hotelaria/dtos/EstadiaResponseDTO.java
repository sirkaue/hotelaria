package hotelaria.projeto.hotelaria.dtos;

import java.util.List;

public record EstadiaResponseDTO(
        Long id,
        double valorTotal,
        List<ItemConsumidoResponseDTO> itensConsumidos
) {}

