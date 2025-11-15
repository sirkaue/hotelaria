package hotelaria.projeto.hotelaria.dtos;

public record RegistrarCheckInRequestDTO(
        Long estadiaId,
        Long quartoId,          // se você usar quarto
        Long hospedeId          // se existir
) {}

