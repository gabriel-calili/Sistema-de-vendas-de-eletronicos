package br.ufop.projeto.modelo;

public class Smartphone extends Produto {
    private String processador;
    private int qntRAM;
    private int qntMemoria;
    private double tamTela;

    public Smartphone(int id, String nome, String fabricante, double preco, int quantidade, int qntMinima,
                      String processador, int qntRAM, int qntMemoria, double tamTela) {
        super(id, nome, fabricante, preco, quantidade, qntMinima);
        this.processador = processador;
        this.qntRAM = qntRAM;
        this.qntMemoria = qntMemoria;
        this.tamTela = tamTela;
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

    public double getTamTela() {
        return tamTela;
    }

    public void setTamTela(double tamTela) {
        this.tamTela = tamTela;
    }

    @Override
    public String getDescricaoCompleta() {
        return "Smartphone: " + getNome() + " | Tela: " + tamTela + "\" | Proc: " + processador + " | RAM: " + qntRAM + "GB | Armazenamento: " + qntMemoria + "GB | Preco: R$ " + getPreco();
    }
}
