package br.com.alura.leilao.model;

public class LeilaoBuilder {
    private final Leilao leilao;

    public LeilaoBuilder(String descricao) {
        this.leilao = new Leilao(descricao);
    }

    public LeilaoBuilder lance(Usuario usuario, double valor) {
        this.leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao build() {
        return leilao;
    }
}
