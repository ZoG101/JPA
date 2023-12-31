package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ITEMPEDIDO")
public class ItemPedido {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private BigDecimal precoUnitario;
    private int qtn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;
    
    public ItemPedido(int qtn, Pedido pedido, Produto produto) {

        this.qtn = qtn;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = produto.getPreco();

    }

    public ItemPedido() {}

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public BigDecimal getPrecoUnitario() {

        return precoUnitario;

    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {

        this.precoUnitario = precoUnitario;

    }

    public int getQtn() {

        return this.qtn;

    }

    public void setQtn(int qtn) {

        this.qtn = qtn;

    }

    public Pedido getPedido() {

        return pedido;

    }

    public void setPedido(Pedido pedido) {

        this.pedido = pedido;

    }

    public Produto getProduto() {

        return produto;
        
    }

    public void setProduto(Produto produto) {

        this.produto = produto;

    }

}