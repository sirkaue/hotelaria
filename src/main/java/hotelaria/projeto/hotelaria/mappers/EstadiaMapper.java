package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.EstadiaConsumoRequestDTO;
import hotelaria.projeto.hotelaria.dtos.EstadiaResponseDTO;
import hotelaria.projeto.hotelaria.dtos.ItemConsumidoResponseDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.model.ServicoExtra;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EstadiaMapper {

    public static ItemConsumido toEntity(EstadiaConsumoRequestDTO dto, ServicoExtra servico) {
        ItemConsumido item = new ItemConsumido();
        item.setQuantidade(dto.quantidade());
        item.setServicoExtra(servico);
        return item;
    }

    public static EstadiaResponseDTO toResponse(Estadia estadia) {
        return new EstadiaResponseDTO(
                estadia.getId(),
                estadia.getValorTotal(),
                estadia.getItensConsumidos()
                        .stream()
                        .map(EstadiaMapper::toItemResponse)
                        .collect(Collectors.toList())
        );
    }

    private static ItemConsumidoResponseDTO toItemResponse(ItemConsumido item) {
        return new ItemConsumidoResponseDTO(
                item.getId(),
                item.getQuantidade(),
                item.getDataConsumo(),
                item.getValorCobrado(),
                item.getServicoExtra().getId()
        );
    }
}
