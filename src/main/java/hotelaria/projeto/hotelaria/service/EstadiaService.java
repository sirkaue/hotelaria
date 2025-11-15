package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.dtos.EstadiaConsumoRequestDTO;
import hotelaria.projeto.hotelaria.dtos.EstadiaResponseDTO;
import hotelaria.projeto.hotelaria.mappers.EstadiaMapper;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.model.ServicoExtra;
import hotelaria.projeto.hotelaria.repository.EstadiaRepository;
import hotelaria.projeto.hotelaria.repository.ServicoExtraRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadiaService {

    private final EstadiaRepository estadiaRepository;
    private final ServicoExtraRepository servicoExtraRepository;

    public EstadiaService(
            EstadiaRepository estadiaRepository,
            ServicoExtraRepository servicoExtraRepository
    ) {
        this.estadiaRepository = estadiaRepository;
        this.servicoExtraRepository = servicoExtraRepository;
    }

    public EstadiaResponseDTO adicionarConsumo(Long estadiaId, EstadiaConsumoRequestDTO dto) {

        Estadia estadia = buscarEstadia(estadiaId);
        ServicoExtra servico = buscarServico(dto.servicoExtraId());

        ItemConsumido item = EstadiaMapper.toEntity(dto, servico);

        estadia.adicionarItemConsumido(item);
        estadia.setValorTotal(calcularValorTotal(estadia));

        estadiaRepository.save(estadia);

        return EstadiaMapper.toResponse(estadia);
    }

    public EstadiaResponseDTO calcularValorTotal(Long estadiaId) {
        Estadia estadia = buscarEstadia(estadiaId);

        estadia.setValorTotal(calcularValorTotal(estadia));

        estadiaRepository.save(estadia);

        return EstadiaMapper.toResponse(estadia);
    }

    private double calcularValorTotal(Estadia estadia) {
        return estadia.getItensConsumidos()
                .stream()
                .mapToDouble(ItemConsumido::getValorCobrado)
                .sum();
    }

    private Estadia buscarEstadia(Long id) {
        return estadiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estadia não encontrada."));
    }

    private ServicoExtra buscarServico(Long id) {
        return servicoExtraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço extra não encontrado."));
    }
}
