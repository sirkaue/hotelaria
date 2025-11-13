package hotelaria.projeto.hotelaria.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico_extra")
public class ServicoExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descriocao;
    private Double preco;

    // --- MAPEAMENTO DA ASSOCIAÇÃO ---
    /**
     * UM ServicoExtra (ex: "Coca-Cola") pode estar em MUITOS ItensConsumidos.
     * * "mappedBy = "servicoExtra"" -> Aponta para o campo na classe ItemConsumido
     * que gerencia o relacionamento.
     * * Note a ausência de 'cascade'. Não queremos que, ao deletar um
     * ServicoExtra do catálogo (ex: "não vendemos mais Coca-Cola"),
     * o histórico de consumo seja apagado.
     */
    @OneToMany(
            mappedBy = "servicoExtra",
            fetch = FetchType.LAZY
            // Sem cascade
    )
    private List<ItemConsumido> itensConsumidos = new ArrayList<>();

    public ServicoExtra() {
    }

    public ServicoExtra(Long id, String nome, String descriocao, Double preco, List<ItemConsumido> itensConsumidos) {
        this.id = id;
        this.nome = nome;
        this.descriocao = descriocao;
        this.preco = preco;
        this.itensConsumidos = itensConsumidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescriocao() {
        return descriocao;
    }

    public void setDescriocao(String descriocao) {
        this.descriocao = descriocao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<ItemConsumido> getItensConsumidos() {
        return itensConsumidos;
    }
}