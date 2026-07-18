package br.ufop.projeto.modelo;

import java.util.List;

public class Comprador extends Usuario {

    public Comprador(int id, String nome) {
        super(id, nome, Usuario.COMPRADOR);
    }

    public void realizarCompra(Compra compra) {
        compra.atualizarEstoque();
        System.out.println("Compra de reposicao realizada por: " + getNome());
    }

    public void gerarRelatorioCompras(List<Compra> compras) {
        System.out.println("\n--- RELATORIO DE COMPRAS ---");
        double total = 0;
        for (Compra c : compras) {
            System.out.println("ID: " + c.getId() + " | Data: " + c.getData() + " | Valor: R$ " + c.getValor());
            total += c.getValor();
        }
        System.out.println("Total investido em estoque: R$ " + total);
        System.out.println("----------------------------\n");
    }
}
