package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valorTotal;
	private LocalDate dataPedido = LocalDate.now();

	//@Enumerated(EnumType.STRING)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	/*
	@JoinTable()
	@ManyToMany
	*/

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	public Pedido () {}

	public Pedido(br.com.alura.loja.modelo.Cliente cliente) {

		this.cliente = cliente;
		this.valorTotal = new BigDecimal(0);

	}

	public void adicionarItem(ItemPedido item) {

		item.setPedido(this);
		itens.add(item);
		this.addValorPedido(item);

	}

	public LocalDate getdataPedido() {

		return dataPedido;

	}

	public void setdataPedido(LocalDate dataPedido) {

		this.dataPedido = dataPedido;

	}

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public BigDecimal getvalorTotal() {

		return valorTotal;

	}

	public void setValorTotal(BigDecimal valorTotal) {

		this.valorTotal = valorTotal;
		
	}

	public void addValorPedido (ItemPedido i) {

		this.valorTotal = i.getPrecoUnitario().multiply(new BigDecimal(i.getQtn()));

	}

}
