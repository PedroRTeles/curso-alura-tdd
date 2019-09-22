package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {
    public static final double DELTA = 0.001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");

    @Test
    public void deve_devolverDescricao_criarUmLeilao() {
        String descricaoDevolvida = CONSOLE.getDescricao();
        
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_devolverMaiorLance_QuandoRecebeUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        double maiorLanceDEvolvido = CONSOLE.getMaiorLance();

        assertEquals(200.00, maiorLanceDEvolvido, DELTA);
    }

    @Test
    public void deve_devolverMaiorLance_quandoRecebeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.00));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.00, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_devolverMenorLance_QuandoRecebeUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.00, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_devolverMenorrLance_quandoRecebeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.00));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.00, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_quandoReceberTresLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.00));
        CONSOLE.propoe(new Lance(ALEX, 400.00));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_quandoNaoReceberNenhumLance() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_devolverTresMaioresLances_quandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_quandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.00));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_devolverTresMaioresLances_quandoRecebeMaisDeTresLances() {
        final Usuario FRAN = new Usuario("Fran");

        CONSOLE.propoe(new Lance(ALEX, 200.00));
        CONSOLE.propoe(new Lance(FRAN, 300.00));
        CONSOLE.propoe(new Lance(ALEX, 400.00));
        CONSOLE.propoe(new Lance(FRAN, 600.00));

        List<Lance> tresMaioresLancesDevolvidosParaTresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaTresLances.size());
        assertEquals(600.00, tresMaioresLancesDevolvidosParaTresLances.get(0).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvidosParaTresLances.get(1).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvidosParaTresLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(ALEX, 800.00));

        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(800.00, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(600.00, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_devolverZeroParaMaiorLance_quandoNaoTemLance() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_devolverZeroParaMenorLance_quandoNaoTemLance() {
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_adicionarLance_quandoForMenorQueMaiorLance() {
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.00));

        int quantidadeLancesDevolvida = CONSOLE.getQuantidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_adicionarLance_quandoUsuarioForOMesmoDoUltimoLance() {
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        CONSOLE.propoe(new Lance(new Usuario("Alex"), 600.00));

        int quantidadeLancesDevolvida = CONSOLE.getQuantidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_adicionarLance_quandoUsuarioDerCincoLances() {
        Usuario FRAN = new Usuario("Fran");
        Leilao console = new LeilaoBuilder("Console")
                .lance(ALEX, 100.00)
                .lance(FRAN, 200.00)
                .lance(ALEX, 300.00)
                .lance(FRAN, 400.00)
                .lance(ALEX, 500.00)
                .lance(FRAN, 600.00)
                .lance(ALEX, 700.00)
                .lance(FRAN, 800.00)
                .lance(ALEX, 900.00)
                .lance(FRAN, 1000.00)
                .lance(ALEX, 1100.00)
                .lance(FRAN, 1200.00)
                .build();

        int quantidadeLancesDevolvidos = console.getQuantidadeLances();

        assertEquals(10, quantidadeLancesDevolvidos);
    }
}