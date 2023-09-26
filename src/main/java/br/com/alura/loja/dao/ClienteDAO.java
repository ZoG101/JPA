package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Produto;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO (EntityManager em) {

        this.em = em;

    }

    public void cadastrar (Cliente produto) {

        this.em.persist(produto);

    }

    public void atualizar (Produto prodduto) {

        this.em.merge(prodduto);

    }

    public Cliente buscarPorId (long id) {

        return this.em.find(Cliente.class, id);

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
    
}
