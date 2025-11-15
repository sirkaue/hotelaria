package hotelaria.projeto.hotelaria.service;

import hotelaria.projeto.hotelaria.model.ServicoExtra;
import hotelaria.projeto.hotelaria.repository.ServicoExtraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoExtraService {

    private final ServicoExtraRepository repository;

    public ServicoExtraService(ServicoExtraRepository repository) {
        this.repository = repository;
    }

    private ServicoExtra buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Serviço extra não encontrado."));
    }

    @Transactional
    public void atualizarPreco(Long servicoId, double novoPreco) {
        if (novoPreco < 0) {
            throw new IllegalArgumentException("O preço do serviço não pode ser negativo.");
        }

        ServicoExtra servico = buscarPorId(servicoId);
        servico.setPreco(novoPreco);
    }
}
