package hotelaria.projeto.hotelaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hotelaria.projeto.hotelaria.enums.StatusLimpeza;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa_limpeza")
public class TarefaLimpeza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAgendada;
    private StatusLimpeza statusLimpeza;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario_id")
    @JsonBackReference("funcionario-tarefa")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_quarto_id")
    @JsonBackReference("quarto-tarefa")
    private Quarto quarto;

    public TarefaLimpeza() {
    }

    public TarefaLimpeza(Long id, LocalDateTime dataAgendada, StatusLimpeza statusLimpeza, Funcionario funcionario, Quarto quarto) {
        this.id = id;
        this.dataAgendada = dataAgendada;
        this.statusLimpeza = statusLimpeza;
        this.funcionario = funcionario;
        this.quarto = quarto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDateTime dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public StatusLimpeza getStatusLimpeza() {
        return statusLimpeza;
    }

    public void setStatusLimpeza(StatusLimpeza statusLimpeza) {
        this.statusLimpeza = statusLimpeza;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
