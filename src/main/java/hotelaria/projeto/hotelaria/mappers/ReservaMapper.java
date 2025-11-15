package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.ReservaAlterarDatasRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservaMapper {

    public static LocalDateTime[] toDatas(ReservaAlterarDatasRequestDTO dto) {
        return new LocalDateTime[]{
                dto.dataInicio(),
                dto.dataFim()
        };
    }
}