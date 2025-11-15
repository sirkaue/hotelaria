package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para atribuir um funcionário a uma tarefa de limpeza")
public record AtribuirFuncionarioRequestDTO(
        @Schema(description = "ID do funcionário a ser atribuído", example = "3", required = true)
        Long funcionarioId
) {}
