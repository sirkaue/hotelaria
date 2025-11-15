package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.enums.StatusQuarto;
import hotelaria.projeto.hotelaria.enums.TipoQuarto;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.Quarto;
import hotelaria.projeto.hotelaria.repository.QuartoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    private Quarto buscarPorId(Long id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado"));
    }

    private void validarCapacidadePorTipo(Quarto quarto) {
        int capacidade = quarto.getCapacidade();

        switch (quarto.getTipoQuarto()) {
            case STANDARD -> {
                if (capacidade > 2) {
                    throw new IllegalArgumentException("STANDARD comporta no máximo 2 hóspedes.");
                }
            }
            case DELUXE -> {
                if (capacidade > 4) {
                    throw new IllegalArgumentException("DELUXE comporta no máximo 4 hóspedes.");
                }
            }
            case SUITE -> {
                if (capacidade > 6) {
                    throw new IllegalArgumentException("SUITE comporta no máximo 6 hóspedes.");
                }
            }
        }
    }

    public double calcularPrecoBase(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);

        return switch (quarto.getTipoQuarto()) {
            case STANDARD -> 150.0;
            case DELUXE -> 300.0;
            case SUITE -> 550.0;
        };
    }

    public boolean permiteServicosPremium(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        return quarto.getTipoQuarto() == TipoQuarto.SUITE;
    }
    
    @Transactional
    public void adicionarEstadia(Long quartoId, Estadia estadia) {
        Quarto quarto = buscarPorId(quartoId);

        // Validação de capacidade por tipo
        validarCapacidadePorTipo(quarto);

        quarto.getEstadias().add(estadia);
        estadia.setQuarto(quarto);

        // Ao receber uma estadia ativa → quarto fica OCUPADO
        quarto.setStatusQuarto(StatusQuarto.OCUPADO);
    }

    @Transactional
    public void removerEstadia(Long quartoId, Estadia estadia) {
        Quarto quarto = buscarPorId(quartoId);
        quarto.getEstadias().remove(estadia);

        // Se removeu e não há estadias ativas → DISPONÍVEL
        if (quarto.getEstadias().isEmpty()) {
            quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);
        }
    }

    public boolean verificarDisponibilidade(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        return quarto.getStatusQuarto() == StatusQuarto.DISPONIVEL;
    }

    @Transactional
    public void marcarComoLimpo(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);
    }

    @Transactional
    public void marcarComoOcupado(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        quarto.setStatusQuarto(StatusQuarto.OCUPADO);
    }

    @Transactional
    public void marcarParaLimpeza(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        quarto.setStatusQuarto(StatusQuarto.EM_LIMPEZA);
    }

    @Transactional
    public void marcarParaManutencao(Long quartoId) {
        Quarto quarto = buscarPorId(quartoId);
        quarto.setStatusQuarto(StatusQuarto.MANUTENCAO);
    }
}
