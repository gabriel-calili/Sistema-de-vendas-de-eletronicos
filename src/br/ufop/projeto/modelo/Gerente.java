package br.ufop.projeto.modelo;

import java.util.List;

public class Gerente extends Usuario {
    private String cpf;

    public Gerente(int id, String nome, String cpf) {
        super(id, nome, Usuario.GERENTE);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void definirQntMinima(Produto[] produtos) {
        if (produtos == null) return;
        for (Produto p : produtos) {
            if (p != null && p.getQntMinima() == 0) {
                p.setQntMinima(5);
            }
        }
        System.out.println("Estoque minimo padrao revisado pelo gerente: " + getNome());
    }

    public void definirQntMinima(Produto produto, int qntMinima) {
        produto.setQntMinima(qntMinima);
        System.out.println("Produto: " + produto.getNome() + " | Estoque minimo definido para: " + qntMinima);
    }

    public void gerarRelatorio(Produto[] produtos) {
        if (produtos == null) return;
        System.out.println("\n--- RELATORIO DE ESTOQUE ---");
        for (Produto p : produtos) {
            if (p != null) {
                String status = "OK";
                if (p.getQuantidade() < p.getQntMinima()) {
                    status = "ESTOQUE BAIXO";
                }
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Qtd: " + p.getQuantidade() + " (Min: " + p.getQntMinima() + ") | Status: " + status);
            }
        }
        System.out.println("----------------------------\n");
    }

    public void gerarRelatorioLucro(List<Venda> vendas, List<Compra> compras) {
        double totalVendas = 0;
        double totalCompras = 0;
        for (Venda v : vendas) {
            totalVendas += v.getValor();
        }
        for (Compra c : compras) {
            totalCompras += c.getValor();
        }
        System.out.println("\n--- RELATORIO DE LUCRO ---");
        System.out.println("Total Vendas: R$ " + totalVendas);
        System.out.println("Total Compras (Custo): R$ " + totalCompras);
        System.out.println("Lucro Liquido: R$ " + (totalVendas - totalCompras));
        System.out.println("--------------------------\n");
    }
}
