package hotelaria.projeto.hotelaria.repository;

import hotelaria.projeto.hotelaria.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
