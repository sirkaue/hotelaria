package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Informações da estadia, incluindo consumos e valor total")
public record EstadiaResponseDTO(
        @Schema(description = "ID da estadia", example = "10")
        Long id,

        @Schema(description = "Valor total da estadia, incluindo todos os consumos", example = "450.75")
        double valorTotal,

        @Schema(description = "Lista de itens consumidos durante a estadia")
        List<ItemConsumidoResponseDTO> itensConsumidos
) {}
