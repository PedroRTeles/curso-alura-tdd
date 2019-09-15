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
    public void deve_devolverMaiorLance_quandoRecebeUmLanceEmOrdemDecrescente() {
        CONSOLE.propoe(new Lance(ALEX, 2000.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 900.00));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(2000.00, maiorLanceDevolvido, DELTA);
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
    public void deve_devolverMenorLance_quandoRecebeUmLanceEmOrdemDecrescente() {
        CONSOLE.propoe(new Lance(ALEX, 2000.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 900.00));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(900.00, menorLanceDevolvido, DELTA);
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
}