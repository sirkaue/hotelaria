package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.RegistrarCheckInRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarCheckOutRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarConsumoRequestDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.model.ServicoExtra;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public static Estadia toEstadiaForCheckIn(RegistrarCheckInRequestDTO dto) {
        Estadia e = new Estadia();
        e.setId(dto.estadiaId());
        return e;
    }

    public static Estadia toEstadiaForCheckOut(RegistrarCheckOutRequestDTO dto) {
        Estadia e = new Estadia();
        e.setId(dto.estadiaId());
        return e;
    }

    public static ItemConsumido toItemConsumido(RegistrarConsumoRequestDTO dto, ServicoExtra servico) {
        ItemConsumido item = new ItemConsumido();
        item.setQuantidade(dto.quantidade());
        item.setServicoExtra(servico);
        return item;
    }
}
