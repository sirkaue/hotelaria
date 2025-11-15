package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.ServicoExtraAtualizarPrecoRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ServicoExtraMapper {

    public static double toNovoPreco(ServicoExtraAtualizarPrecoRequestDTO dto) {
        return dto.novoPreco();
    }
}
