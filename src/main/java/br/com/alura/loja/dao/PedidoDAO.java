package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.vo.RelatorioDeVendas;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO (EntityManager em) {

        this.em = em;

    }

    public void cadastrar (Pedido produto) {

        this.em.persist(produto);

    }

    public void atualizar (Produto prodduto) {

        this.em.merge(prodduto);

    }

    public Produto buscarPorId (long id) {

        return this.em.find(Produto.class, id);

    }

    public List<Produto> buscarTodos () {

        String jpql = "SELECT p FROM Produto p";

        return this.em.createQuery(jpql, Produto.class).getResultList();

    }

    public List<Produto> buscarPorNome (String nome) {

        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        //String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";

        return this.em.createQuery(jpql, Produto.class)
        .setParameter("nome", nome)
        //.setParameter(1, nome)
        .getResultList();

    }

    public List<Produto> buscarPorNomeCategoria (String nome) {

        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";

        return this.em.createQuery(jpql, Produto.class)
        .setParameter("nome", nome)
        .getResultList();

    }

    public BigDecimal buscarPreco (String nome) {

        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";

        return this.em.createQuery(jpql, BigDecimal.class)
        .setParameter("nome", nome)
        .getSingleResult();

    }

    public BigDecimal valorTotalVendido () {

        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();

    }

    public List<RelatorioDeVendas> qtnTotalVendas () {

        String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendas("
        + "produto.nome, "
        + "SUM(item.qtn) as quantidadeTotal, " 
        + "MAX(pedido.dataPedido)) "
        + "FROM Pedido pedido "
        + "JOIN pedido.itens item "
        + "JOIN item.produto produto "
        + "GROUP BY produto.nome "
        + "ORDER BY quantidadeTotal DESC";
        List<RelatorioDeVendas> retorno = em.createQuery(jpql, RelatorioDeVendas.class).getResultList();
        //this.ordenaLista(retorno);
        //this.inverteLista(retorno);
        return retorno;

    }

    public <T extends Comparable<T>> void ordenaLista (List<? extends T> lista) {

        Collections.sort(lista);

    }

    public <T extends Comparable<T>> void inverteLista (List<? extends T> lista) {

        Collections.reverse(lista);

    }

    /*
    public List<Object[]> qtnTotalVendas () {

        String jpql = "SELECT produto.nome, " 
        + "SUM(item.qtn), " 
        + "MAX(pedido.dataPedido) " 
        + "FROM Pedido pedido " 
        + "JOIN pedido.itens item " 
        + "JOIN item.produto produto " 
        + "GROUP BY produto.nome, item.qtn " 
        + "ORDER BY item.qtn DESC";
        return em.createQuery(jpql, Object[].class).getResultList();

    }
    */

    public Pedido buscarPedidoComCliente (Long id) {

        return em.createQuery("SELECT p JOIN FETCH p.cliente FROM Pedido p WHERE p.id = :id", Pedido.class).setParameter("id", id).getSingleResult();

    }

}
