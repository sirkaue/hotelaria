package hotelaria.projeto.hotelaria.dtos;

public record RegistrarConsumoRequestDTO(
        Long estadiaId,
        Long servicoExtraId,
        int quantidade
) {
}

