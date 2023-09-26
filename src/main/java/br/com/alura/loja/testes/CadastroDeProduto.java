package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendas;

public class CadastroDeProduto {
	
	public static void main (String[] args) {

		//cadastroDeProduto();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtodao = new ProdutoDAO(em);
		Produto p = produtodao.buscarPorId(00001);

		ClienteDAO clienteDAO = new ClienteDAO(em);
		Cliente cliente = clienteDAO.buscarPorId(00001);
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, p));

		PedidoDAO pedidoDAO = new PedidoDAO(em);
		em.getTransaction().begin();
		pedidoDAO.cadastrar(pedido);
		em.getTransaction().commit();
		BigDecimal res = pedidoDAO.valorTotalVendido();
		System.out.println(res);

		List<RelatorioDeVendas> res2 = pedidoDAO.qtnTotalVendas();
		res2.forEach(System.out::println);

		/* 
		List<Object[]> res2 = pedidoDAO.qtnTotalVendas();
		
		for (Object[] objects : res2) {

			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
			}
			
		}
		*/

		List<Produto> ps = produtodao.buscarPorParam("", new BigDecimal(1500), null);
		for (Produto produto : ps) {
			produto.getNome();
		}

		em.find(Categoria.class, new CategoriaId("CELULARES", "XPTO"));

	}

	private static void cadastroDeProduto() {

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO produtodao = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		List<Produto> p = produtodao.buscarPorNome("IPhone 12 Pro Max");
		System.out.println(p);

		//Categoria celulares = new Categoria("CELULARES");

		Categoria celulares = categoriaDAO.buscarPorId(1);
		Produto celular = new Produto("IPhone 12 Pro Max", "Muito legal", new BigDecimal("1500"), celulares);
		
		Cliente cliente = new Cliente("Davi", "123345");
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		categoriaDAO.cadastrar(celulares);
		produtodao.cadastrar(celular);
		//em.getTransaction().rollback();
		//em.getTransaction().commit();
		clienteDAO.cadastrar(cliente);
		em.flush();
		//em.close();
		em.clear();

		//celulares = em.merge(celulares);
		//celulares.setNome("1234");
		//em.flush();
		//em.remove(celulares);
		//em.find(null, null, null, null);
		//em.createQuery(null, null);
		//celular = em.merge(celular);
		//em.remove(celular);
		//em.flush();
		em.getTransaction().commit();
		em.close();

	}

}
