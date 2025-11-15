package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.enums.StatusLimpeza;
import hotelaria.projeto.hotelaria.model.Funcionario;
import hotelaria.projeto.hotelaria.model.TarefaLimpeza;
import hotelaria.projeto.hotelaria.repository.TarefaLimpezaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaLimpezaService {

    private final TarefaLimpezaRepository repository;

    public TarefaLimpezaService(TarefaLimpezaRepository repository) {
        this.repository = repository;
    }

    private TarefaLimpeza buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa de limpeza não encontrada"));
    }

    @Transactional
    public void iniciarTarefa(Long tarefaId) {
        TarefaLimpeza tarefa = buscarPorId(tarefaId);

        if (tarefa.getStatusLimpeza() == StatusLimpeza.CONCLUIDA) {
            throw new IllegalStateException("A tarefa já foi concluída e não pode ser reiniciada.");
        }

        tarefa.setStatusLimpeza(StatusLimpeza.EM_ANDAMENTO);
    }

    @Transactional
    public void concluirTarefa(Long tarefaId) {
        TarefaLimpeza tarefa = buscarPorId(tarefaId);

        if (tarefa.getStatusLimpeza() != StatusLimpeza.EM_ANDAMENTO) {
            throw new IllegalStateException("Só é possível concluir uma tarefa que está em andamento.");
        }

        tarefa.setStatusLimpeza(StatusLimpeza.CONCLUIDA);
    }

    @Transactional
    public void atribuirFuncionario(Long tarefaId, Funcionario funcionario) {
        TarefaLimpeza tarefa = buscarPorId(tarefaId);
        tarefa.setFuncionario(funcionario);
    }
}
