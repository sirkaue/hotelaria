package hotelaria.projeto.hotelaria.model;

import hotelaria.projeto.hotelaria.enums.StatusEstadia;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estadias")
public class Estadia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCheckin;
    private LocalDateTime dataCheckOut;
    private double valorTotal;
    private StatusEstadia statusEstadia;

    @ManyToOne
    @JoinColumn(name = "fk_funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_quarto_id")
    private Quarto quarto;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY é bom para performance
    @JoinColumn(name = "fk_hospede_id", nullable = false) // Uma estadia deve ter um hóspede
    private Hospede hospede;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_reserva_id", unique = true) // unique = true garante que uma reserva só gere uma estadia
    private Reserva reserva;

    @OneToMany(mappedBy = "estadia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemConsumido> itensConsumidos = new ArrayList<>();

    public Estadia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDateTime dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDateTime getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDateTime dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusEstadia getStatusEstadia() {
        return statusEstadia;
    }

    public void setStatusEstadia(StatusEstadia statusEstadia) {
        this.statusEstadia = statusEstadia;
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

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<ItemConsumido> getItensConsumidos() {
        return itensConsumidos;
    }

    public void adicionarItemConsumido(ItemConsumido item) {
        this.itensConsumidos.add(item);
        item.setEstadia(this); // Garante a consistência dos dois lados
    }

    public void removerItemConsumido(ItemConsumido item) {
        this.itensConsumidos.remove(item);
        item.setEstadia(null);
    }
}
