package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendas implements Comparable<RelatorioDeVendas> {

    private String nomeProduto;
    private Long quantidadeDeVendasLong;
    private LocalDate dataVenda;

    public RelatorioDeVendas (String nomeProduto, Long quantidadeDeVendasLong, LocalDate dataVenda) {

        this.nomeProduto = nomeProduto;
        this.quantidadeDeVendasLong = quantidadeDeVendasLong;
        this.dataVenda = dataVenda;

    }

    public String getNomeProduto() {

        return nomeProduto;

    }

    public Long getQuantidadeDeVendasLong() {

        return quantidadeDeVendasLong;

    }

    public LocalDate getDataVenda() {

        return dataVenda;

    }

    @Override
    public int compareTo (RelatorioDeVendas o) {
        
        return o.getQuantidadeDeVendasLong().compareTo(this.quantidadeDeVendasLong);
        
    }

    @Override
    public String toString() {
        return "RelatorioDeVendas [nomeProduto=" + nomeProduto + ", quantidadeDeVendasLong=" + quantidadeDeVendasLong
                + ", dataVenda=" + dataVenda + "]";
    } 
    
}
