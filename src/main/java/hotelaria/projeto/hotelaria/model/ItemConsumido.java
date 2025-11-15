package hotelaria.projeto.hotelaria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_consumido")
public class ItemConsumido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;
    private LocalDateTime dataConsumo;
    private double valorCobrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estadia_id", nullable = false)
    private Estadia estadia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_servico_extra_id", nullable = false)
    private ServicoExtra servicoExtra;

    public ItemConsumido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public ServicoExtra getServicoExtra() {
        return servicoExtra;
    }

    public void setServicoExtra(ServicoExtra servicoExtra) {
        this.servicoExtra = servicoExtra;
    }

    @PrePersist
    public void prePersist() {
        if (dataConsumo == null) {
            dataConsumo = LocalDateTime.now();
        }

        if (servicoExtra != null) {
            this.valorCobrado = servicoExtra.getPreco();
        }
    }
}