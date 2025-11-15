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

    @OneToMany(mappedBy = "servicoExtra", fetch = FetchType.LAZY)
    private List<ItemConsumido> itensConsumidos = new ArrayList<>();

    public ServicoExtra() {
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
