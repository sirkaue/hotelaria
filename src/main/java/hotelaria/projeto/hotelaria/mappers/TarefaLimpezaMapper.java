package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.AtribuirFuncionarioRequestDTO;
import hotelaria.projeto.hotelaria.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class TarefaLimpezaMapper {

    public static Funcionario toFuncionario(AtribuirFuncionarioRequestDTO dto) {
        Funcionario f = new Funcionario();
        f.setId(dto.funcionarioId());
        return f;
    }
}
