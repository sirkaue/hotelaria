package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.dtos.FuncionarioCreateRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarCheckInRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarCheckOutRequestDTO;
import hotelaria.projeto.hotelaria.dtos.RegistrarConsumoRequestDTO;
import hotelaria.projeto.hotelaria.enums.StatusEstadia;
import hotelaria.projeto.hotelaria.mappers.FuncionarioMapper;
import hotelaria.projeto.hotelaria.model.Estadia;
import hotelaria.projeto.hotelaria.model.Funcionario;
import hotelaria.projeto.hotelaria.model.ItemConsumido;
import hotelaria.projeto.hotelaria.model.ServicoExtra;
import hotelaria.projeto.hotelaria.repository.EstadiaRepository;
import hotelaria.projeto.hotelaria.repository.FuncionarioRepository;
import hotelaria.projeto.hotelaria.repository.ServicoExtraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EstadiaRepository estadiaRepository;
    private final ServicoExtraRepository servicoExtraRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              EstadiaRepository estadiaRepository,
                              ServicoExtraRepository servicoExtraRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.estadiaRepository = estadiaRepository;
        this.servicoExtraRepository = servicoExtraRepository;
    }

    @Transactional
    public void criar(FuncionarioCreateRequestDTO dto) {
        Funcionario f = new Funcionario();
        f.setNome(dto.nome());
        f.setCargo(dto.cargo());
        funcionarioRepository.save(f);
    }

    public void registrarCheckIn(Long funcionarioId, RegistrarCheckInRequestDTO dto) {
        Funcionario funcionario = buscarFuncionario(funcionarioId);

        Estadia estadia = estadiaRepository.findById(dto.estadiaId())
                .orElseThrow(() -> new IllegalArgumentException("Estadia não encontrada."));

        estadia.setFuncionario(funcionario);
        estadia.setDataCheckin(LocalDateTime.now());
        estadia.setStatusEstadia(StatusEstadia.ATIVO);

        estadiaRepository.save(estadia);
    }

    public void registrarCheckOut(Long funcionarioId, RegistrarCheckOutRequestDTO dto) {
        Funcionario funcionario = buscarFuncionario(funcionarioId);

        Estadia estadia = estadiaRepository.findById(dto.estadiaId())
                .orElseThrow(() -> new IllegalArgumentException("Estadia não encontrada."));

        estadia.setFuncionario(funcionario);
        estadia.setDataCheckOut(LocalDateTime.now());
        estadia.setStatusEstadia(StatusEstadia.FINALIZADO);

        estadiaRepository.save(estadia);
    }

    public void registrarConsumo(Long funcionarioId, RegistrarConsumoRequestDTO dto) {
        Funcionario funcionario = buscarFuncionario(funcionarioId);

        Estadia estadia = estadiaRepository.findById(dto.estadiaId())
                .orElseThrow(() -> new IllegalArgumentException("Estadia não encontrada."));

        ServicoExtra servico = servicoExtraRepository.findById(dto.servicoExtraId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço extra não encontrado."));

        ItemConsumido item = FuncionarioMapper.toItemConsumido(dto, servico);

        estadia.adicionarItemConsumido(item);

        // recalcular total
        double total = estadia.getItensConsumidos()
                .stream()
                .mapToDouble(ItemConsumido::getValorCobrado)
                .sum();

        estadia.setValorTotal(total);

        estadiaRepository.save(estadia);
    }

    private Funcionario buscarFuncionario(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
    }
}
