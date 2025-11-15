package hotelaria.projeto.hotelaria.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criação de um hóspede")
public record HospedeCreateRequestDTO(

        @Schema(description = "Nome completo do hóspede", example = "João da Silva")
        String nome,

        @Schema(description = "CPF do hóspede", example = "12345678900")
        String cpf,

        @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
        String telefone,

        @Schema(description = "Email do hóspede", example = "joao.silva@email.com")
        String email
) {}

