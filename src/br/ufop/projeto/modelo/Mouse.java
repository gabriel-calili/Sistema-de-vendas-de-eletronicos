package br.ufop.projeto.modelo;

public class Mouse extends Produto {
    private int dpiMax;
    private boolean fio;

    public Mouse(int id, String nome, String fabricante, double preco, int quantidade, int qntMinima,
                 int dpiMax, boolean fio) {
        super(id, nome, fabricante, preco, quantidade, qntMinima);
        this.dpiMax = dpiMax;
        this.fio = fio;
    }

    public int getDpiMax() {
        return dpiMax;
    }

    public void setDpiMax(int dpiMax) {
        this.dpiMax = dpiMax;
    }

    public boolean isFio() {
        return fio;
    }

    public void setFio(boolean fio) {
        this.fio = fio;
    }

    @Override
    public String getDescricaoCompleta() {
        String conexao = fio ? "Com fio" : "Sem fio";
        return "Mouse: " + getNome() + " | DPI: " + dpiMax + " | Conexao: " + conexao + " | Preco: R$ " + getPreco();
    }
}
