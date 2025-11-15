package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar um funcionário")
public record FuncionarioCreateRequestDTO(

        @Schema(description = "Nome completo do funcionário", example = "Maria Oliveira")
        String nome,

        @Schema(description = "Cargo do funcionário", example = "Recepcionista")
        String cargo
) {}

