package hotelaria.projeto.hotelaria.mappers;

import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarReservaRequestDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.Reserva;
import org.springframework.stereotype.Component;

@Component
public class HospedeMapper {

    public static Reserva toReserva(HospedeAdicionarReservaRequestDTO dto) {
        Reserva r = new Reserva();
        r.setId(dto.reservaId());
        return r;
    }

    public static Estadia toEstadia(HospedeAdicionarEstadiaRequestDTO dto) {
        Estadia e = new Estadia();
        e.setId(dto.estadiaId());
        return e;
    }
}
