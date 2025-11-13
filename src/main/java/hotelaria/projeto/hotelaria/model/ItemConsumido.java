package hotelaria.projeto.hotelaria.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_consumido")
public class ItemConsumido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Atributos próprios da associação ---

    // Ex: Quantos itens desse serviço foram consumidos
    private int quantidade;

    // Ex: O preço exato no momento do consumo (boa prática)
    private double precoNoMomento;

    // Ex: Quando foi consumido
    private LocalDateTime dataConsumo;

    // --- Relacionamentos (Chaves Estrangeiras) ---

    /**
     * Muitos ItensConsumidos pertencem a UMA Estadia.
     * Esta é a "dona" da relação com Estadia.
     * nullable = false garante que um item consumido não pode existir sem uma estadia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estadia_id", nullable = false)
    private Estadia estadia;

    /**
     * Muitos ItensConsumidos referenciam UM ServicoExtra.
     * Ex: Várias pessoas podem consumir "Coca-Cola" (ServicoExtra).
     * Cada consumo é um ItemConsumido diferente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_servico_extra_id", nullable = false)
    private ServicoExtra servicoExtra;

    @PrePersist
    public void prePersist() {
        // Define a data de consumo automaticamente ao salvar
        if (dataConsumo == null) {
            dataConsumo = LocalDateTime.now();
        }
        // Registra o preço do serviço no momento exato do consumo
        if (servicoExtra != null) {
            this.precoNoMomento = servicoExtra.getPreco();
        }
    }

    public ItemConsumido() {
    }

    public ItemConsumido(Long id, int quantidade, double precoNoMomento, LocalDateTime dataConsumo, Estadia estadia, ServicoExtra servicoExtra) {
        this.id = id;
        this.quantidade = quantidade;
        this.precoNoMomento = precoNoMomento;
        this.dataConsumo = dataConsumo;
        this.estadia = estadia;
        this.servicoExtra = servicoExtra;
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

    public double getPrecoNoMomento() {
        return precoNoMomento;
    }

    public void setPrecoNoMomento(double precoNoMomento) {
        this.precoNoMomento = precoNoMomento;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
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
}