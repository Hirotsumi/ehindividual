package br.ucb.ehindividual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Questao implements Serializable {
    @Expose
    @SerializedName("pergunta")
    private String pergunta;
    @Expose
    @SerializedName("alternativas")
    private String[] alternativas;
    @Expose
    @SerializedName("alternativaCorreta")
    private int alternativaCorreta;

    public Questao(String pergunta, String[] alternativas, int alternativaCorreta) {
        this.pergunta = pergunta;
        this.alternativas = alternativas;
        this.alternativaCorreta = alternativaCorreta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public int getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(int alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }
}
