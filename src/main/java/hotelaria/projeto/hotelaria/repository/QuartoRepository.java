package hotelaria.projeto.hotelaria.repository;

import hotelaria.projeto.hotelaria.enums.StatusQuarto;
import hotelaria.projeto.hotelaria.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    List<Quarto> findByStatusQuarto(StatusQuarto statusQuarto);

    boolean existsByNumero(String numero);
}
