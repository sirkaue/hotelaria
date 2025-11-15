package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.QuartoEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import org.springframework.stereotype.Component;

@Component
public class QuartoMapper {

    public static Estadia toEstadia(QuartoEstadiaRequestDTO dto) {
        Estadia e = new Estadia();
        e.setId(dto.estadiaId());
        return e;
    }
}
