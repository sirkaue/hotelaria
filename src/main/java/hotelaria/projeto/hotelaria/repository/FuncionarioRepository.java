package hotelaria.projeto.hotelaria.repository;

import hotelaria.projeto.hotelaria.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
