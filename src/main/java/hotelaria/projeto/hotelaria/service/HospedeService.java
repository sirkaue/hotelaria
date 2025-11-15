package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarEstadiaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeAdicionarReservaRequestDTO;
import hotelaria.projeto.hotelaria.dtos.HospedeCreateRequestDTO;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.Hospede;
import hotelaria.projeto.hotelaria.model.Reserva;
import hotelaria.projeto.hotelaria.repository.EstadiaRepository;
import hotelaria.projeto.hotelaria.repository.HospedeRepository;
import hotelaria.projeto.hotelaria.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospedeService {

    private final HospedeRepository hospedeRepository;
    private final ReservaRepository reservaRepository;
    private final EstadiaRepository estadiaRepository;

    public HospedeService(HospedeRepository hospedeRepository,
                          ReservaRepository reservaRepository,
                          EstadiaRepository estadiaRepository) {
        this.hospedeRepository = hospedeRepository;
        this.reservaRepository = reservaRepository;
        this.estadiaRepository = estadiaRepository;
    }

    @Transactional
    public void criar(HospedeCreateRequestDTO dto) {
        Hospede h = new Hospede();
        h.setNome(dto.nome());
        h.setCpf(dto.cpf());
        h.setTelefone(dto.telefone());
        h.setEmail(dto.email());
        hospedeRepository.save(h);
    }



    @Transactional
    public void adicionarReserva(Long hospedeId, HospedeAdicionarReservaRequestDTO dto) {
        Hospede hospede = hospedeRepository.findById(hospedeId)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado."));

        Reserva reserva = reservaRepository.findById(dto.reservaId())
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada."));

        reserva.setHospede(hospede);
        hospede.getReservas().add(reserva);

        reservaRepository.save(reserva);
    }

    @Transactional
    public void removerReserva(Long hospedeId, Long reservaId) {
        Hospede hospede = hospedeRepository.findById(hospedeId)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado."));

        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));

        hospede.getReservas().remove(reserva);
        reservaRepository.delete(reserva);
    }

    @Transactional
    public void adicionarEstadia(Long hospedeId, HospedeAdicionarEstadiaRequestDTO dto) {
        Hospede hospede = hospedeRepository.findById(hospedeId)
                .orElseThrow(() -> new RuntimeException("Hóspede não encontrado."));

        Estadia estadia = estadiaRepository.findById(dto.estadiaId())
                .orElseThrow(() -> new IllegalArgumentException("Estadia não encontrada."));

        estadia.setHospede(hospede);
        estadiaRepository.save(estadia);
    }

    @Transactional
    public void removerEstadia(Long estadiaId) {
        Estadia estadia = estadiaRepository.findById(estadiaId)
                .orElseThrow(() -> new RuntimeException("Estadia não encontrada."));

        Hospede hospede = estadia.getHospede();
        hospede.getEstadias().remove(estadia);
        estadiaRepository.delete(estadia);
    }
}
