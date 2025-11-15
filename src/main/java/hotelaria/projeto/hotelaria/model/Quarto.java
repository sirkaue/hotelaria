package hotelaria.projeto.hotelaria.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hotelaria.projeto.hotelaria.enums.StatusQuarto;
import hotelaria.projeto.hotelaria.enums.TipoQuarto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private TipoQuarto tipoQuarto;
    private StatusQuarto statusQuarto;
    private Double precoPorNoite;
    private int capacidade;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estadia> estadias = new ArrayList<>();

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("quarto-tarefa")
    private List<TarefaLimpeza> tarefaLimpezas = new ArrayList<>();

    public Quarto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public StatusQuarto getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(StatusQuarto statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public Double getPrecoPorNoite() {
        return precoPorNoite;
    }

    public void setPrecoPorNoite(Double precoPorNoite) {
        this.precoPorNoite = precoPorNoite;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public List<TarefaLimpeza> getTarefaLimpezas() {
        return tarefaLimpezas;
    }
}
