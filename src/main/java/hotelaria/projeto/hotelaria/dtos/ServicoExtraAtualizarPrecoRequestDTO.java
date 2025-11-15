package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para atualizar o preço de um serviço extra")
public record ServicoExtraAtualizarPrecoRequestDTO(
        @Schema(description = "Novo preço do serviço extra", example = "79.90", required = true)
        double novoPreco
) {}
