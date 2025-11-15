package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.enums.StatusEstadia;
import hotelaria.projeto.hotelaria.enums.StatusReserva;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.Reserva;
import hotelaria.projeto.hotelaria.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void confirmarReserva(Long reservaId) {
        Reserva reserva = buscarReserva(reservaId);

        if (reserva.getStatusReserva() == StatusReserva.CANCELADA) {
            throw new IllegalStateException("Não é possível confirmar uma reserva cancelada.");
        }

        if (reserva.getEstadia() != null) {
            throw new IllegalStateException("A reserva já possui estadia vinculada.");
        }

        // Cria automaticamente a estadia vinculada
        Estadia estadia = new Estadia();
        estadia.setDataCheckin(reserva.getDataInicio());
        estadia.setDataCheckOut(reserva.getDataFim());
        estadia.setStatusEstadia(StatusEstadia.ATIVO);
        estadia.setHospede(reserva.getHospede());
        estadia.setReserva(reserva);

        reserva.setEstadia(estadia);
        reserva.setStatusReserva(StatusReserva.CONFIRMADA);

        reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long reservaId) {
        Reserva reserva = buscarReserva(reservaId);

        // Se existir estadia, ela não deve ser removida assim — apenas impedir checkout
        if (reserva.getEstadia() != null &&
                reserva.getEstadia().getStatusEstadia() == StatusEstadia.ATIVO) {
            throw new IllegalStateException("Não é possível cancelar uma reserva que já está ativa em estadia.");
        }

        reserva.setStatusReserva(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);
    }

    public void alterarDatas(Long reservaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        Reserva reserva = buscarReserva(reservaId);

        if (reserva.getStatusReserva() == StatusReserva.CANCELADA) {
            throw new IllegalStateException("Não é possível alterar datas de uma reserva cancelada.");
        }

        reserva.setDataInicio(dataInicio);
        reserva.setDataFim(dataFim);

        // Se a reserva já gerou uma estadia, sincroniza
        if (reserva.getEstadia() != null) {
            reserva.getEstadia().setDataCheckin(dataInicio);
            reserva.getEstadia().setDataCheckOut(dataFim);
        }

        // Ao alterar a reserva, se ela ainda não foi confirmada → continua pendente
        if (reserva.getStatusReserva() != StatusReserva.CONFIRMADA) {
            reserva.setStatusReserva(StatusReserva.PENDENTE);
        }

        reservaRepository.save(reserva);
    }

    private Reserva buscarReserva(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada."));
    }
}
