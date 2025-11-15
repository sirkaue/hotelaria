package hotelaria.projeto.hotelaria.repository;

import hotelaria.projeto.hotelaria.enums.StatusLimpeza;
import hotelaria.projeto.hotelaria.model.TarefaLimpeza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaLimpezaRepository extends JpaRepository<TarefaLimpeza, Long> {

    List<TarefaLimpeza> findByStatusLimpeza(StatusLimpeza status);
}
