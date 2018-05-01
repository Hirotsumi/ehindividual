package br.ucb.ehindividual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class PerguntaActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener {

    TextView tvNomeJogador1;
    TextView tvNomeJogador2;
    TextView tvAlternativaA;
    TextView tvAlternativaB;
    TextView tvAlternativaC;
    TextView tvAlternativaD;
    TextView tvPergunta;
    TextView tvQuestao;
    Button btnProxima;
    String nomeJogador1;
    String nomeJogador2;
    Questao[] questoes;
    RadioGroup rgJogador1;
    RadioGroup rgJogador2;
    int[] pontosJogador1;
    int[] pontosJogador2;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        Objects.requireNonNull(getActionBar()).hide();
        inicializarVariaveis();
        btnProxima.setOnClickListener(this);
        nomeJogador1 = getIntent().getStringExtra("nomeJogador1");
        nomeJogador2 = getIntent().getStringExtra("nomeJogador2");
        tvNomeJogador1.setText(nomeJogador1);
        tvNomeJogador2.setText(nomeJogador2);
        questoes = recuperarQuestoes();
        carregarQuestao();
    }

    private void inicializarVariaveis() {
        tvNomeJogador1 = findViewById(R.id.tvNomeJogador1);
        tvNomeJogador2 = findViewById(R.id.tvNomeJogador2);
        tvAlternativaA = findViewById(R.id.tvAlternativaA);
        tvAlternativaB = findViewById(R.id.tvAlternativaB);
        tvAlternativaC = findViewById(R.id.tvAlternativaC);
        tvAlternativaD = findViewById(R.id.tvAlternativaD);
        rgJogador1 = findViewById(R.id.rgJogador1);
        rgJogador2 = findViewById(R.id.rgJogador2);
        tvPergunta = findViewById(R.id.tvPergunta);
        btnProxima = findViewById(R.id.btnProxima);
        tvQuestao = findViewById(R.id.tvQuestao);
        pontosJogador1 = new int[10];
        pontosJogador2 = new int[10];
        i = 0;
    }

    private Questao[] recuperarQuestoes() {
        Gson gson = new Gson();
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("questoes.json");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (inputStream != null) {
            try {
                Reader reader = new InputStreamReader(inputStream);
                return gson.fromJson(reader, Questao[].class);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnProxima) {
            if (validarRespostas()) {
                gravarRespostas();
            }
        }
    }

    private void carregarQuestao() {
        tvQuestao.setText("Questão " + (i+1));
        tvPergunta.setText(questoes[i].getPergunta());
        tvAlternativaA.setText(questoes[i].getAlternativas()[0]);
        tvAlternativaB.setText(questoes[i].getAlternativas()[1]);
        tvAlternativaC.setText(questoes[i].getAlternativas()[2]);
        tvAlternativaD.setText(questoes[i].getAlternativas()[3]);
    }

    private void gravarRespostas() {
        boolean jogador1Acertou = false;
        boolean jogador2Acertou = false;
        int respostaJogador1 = recuperarIndiceAlternativa(rgJogador1.getCheckedRadioButtonId());
        int respostaJogador2 = recuperarIndiceAlternativa(rgJogador2.getCheckedRadioButtonId());
        if (respostaJogador1 == questoes[i].getAlternativaCorreta()) {
            pontosJogador1[i] = 10;
            jogador1Acertou = true;
        }
        if (respostaJogador2 == questoes[i].getAlternativaCorreta()) {
            pontosJogador2[i] = 10;
            jogador2Acertou = true;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (jogador1Acertou && jogador2Acertou) {
            alert.setMessage("Ambos acertaram!");
        }else if (!jogador1Acertou && jogador2Acertou) {
            alert.setMessage(nomeJogador2 + " acertou a questão! Explique-a para " + nomeJogador1 + ".");
        }else if (jogador1Acertou && !jogador2Acertou) {
            alert.setMessage(nomeJogador1 + " acertou a questão! Explique-a para " + nomeJogador2 + ".");
        }else{
            alert.setMessage("Ambos erraram a questão!");
        }
        alert.setNeutralButton("OK", this);
        alert.setCancelable(false);
        alert.show();
        rgJogador1.clearCheck();
        rgJogador2.clearCheck();
    }

    private int recuperarIndiceAlternativa(int id) {
        switch (id) {
            case R.id.alternativaAJogador1:
                return 0;
            case R.id.alternativaAJogador2:
                return 0;
            case R.id.alternativaBJogador1:
                return 1;
            case R.id.alternativaBJogador2:
                return 1;
            case R.id.alternativaCJogador1:
                return 2;
            case R.id.alternativaCJogador2:
                return 2;
            case R.id.alternativaDJogador1:
                return 3;
            case R.id.alternativaDJogador2:
                return 3;
            default:
                return -1;
        }
    }

    private boolean validarRespostas() {
        if (rgJogador1.getCheckedRadioButtonId() == -1) {
            mostrarMensagem(nomeJogador1 + " deve selecionar uma resposta");
            return false;
        }
        if (rgJogador2.getCheckedRadioButtonId() == -1) {
            mostrarMensagem(nomeJogador2 + " deve selecionar uma resposta");
            return false;
        }
        return true;
    }

    private void mostrarMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        this.i++;
        if (this.i <= questoes.length) {
            if (this.i == questoes.length) {
                Intent intent = new Intent(this, RevisarActivity.class);
                intent.putExtra("pontosJogador1",pontosJogador1);
                intent.putExtra("pontosJogador2",pontosJogador2);
                intent.putExtra("nomeJogador1", nomeJogador1);
                intent.putExtra("nomeJogador2", nomeJogador2);
                startActivity(intent);
                finish();
                return;
            }
            carregarQuestao();
            if (this.i == questoes.length - 1) {
                btnProxima.setText("Finalizar");
            }
        }
    }
}
