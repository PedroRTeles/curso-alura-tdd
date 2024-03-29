package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        double valorLance = lance.getValor();

        if (lanceInvalido(lance)) return;

        lances.add(lance);

        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;

        Collections.sort(lances);

        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if(lances.size() == 1) {
            menorLance = valorLance;
            maiorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceInvalido(Lance lance) {
        if (valorLanceForMenor(lance)) return true;

        if(!lances.isEmpty()) {
            Usuario usuarioNovo = lance.getUsuario();
            return forOMesmoUsuario(usuarioNovo) || usuarioDeuCincoLances(usuarioNovo);
        }
        return false;
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int lancesDoUsuario = 0;

        for (Lance l: lances) {
            Usuario usuarioExistente = l.getUsuario();

            if(usuarioExistente.equals(usuarioNovo)) {
                lancesDoUsuario++;

                if(lancesDoUsuario == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean forOMesmoUsuario(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();

        if(usuarioNovo.equals(ultimoUsuario)) {
            return true;
        }
        return false;
    }

    private boolean valorLanceForMenor(Lance lance) {
        if(maiorLance > lance.getValor()) {
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if(valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if(valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMenorLance() {
        return menorLance;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidadeLances() {
        return lances.size();
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeMaximaDeLances = lances.size();

        if(quantidadeMaximaDeLances > 3) {
            quantidadeMaximaDeLances = 3;
        }

        return lances.subList(0, quantidadeMaximaDeLances);
    }
}
