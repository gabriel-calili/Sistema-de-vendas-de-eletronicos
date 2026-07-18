package br.ufop.projeto.modelo;

public class Notebook extends Produto {
    private String processador;
    private int qntRAM;
    private int qntMemoria;

    public Notebook(int id, String nome, String fabricante, double preco, int quantidade, int qntMinima,
                    String processador, int qntRAM, int qntMemoria) {
        super(id, nome, fabricante, preco, quantidade, qntMinima);
        this.processador = processador;
        this.qntRAM = qntRAM;
        this.qntMemoria = qntMemoria;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getQntRAM() {
        return qntRAM;
    }

    public void setQntRAM(int qntRAM) {
        this.qntRAM = qntRAM;
    }

    public int getQntMemoria() {
        return qntMemoria;
    }

    public void setQntMemoria(int qntMemoria) {
        this.qntMemoria = qntMemoria;
    }

    @Override
    public String getDescricaoCompleta() {
        return "Notebook: " + getNome() + " | Proc: " + processador + " | RAM: " + qntRAM + "GB | SSD: " + qntMemoria + "GB | Preco: R$ " + getPreco();
    }
}
