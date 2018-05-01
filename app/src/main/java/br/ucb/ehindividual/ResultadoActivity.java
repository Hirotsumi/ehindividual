package br.ucb.ehindividual;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ResultadoActivity extends Activity {

    TextView tvNome1;
    TextView tvNome2;
    TextView tvPontuacao1;
    TextView tvPontuacao2;
    TextView tvPontuacaoTotal;
    String nomeJogador1;
    String nomeJogador2;
    int[] pontosJogador1;
    int[] pontosJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Objects.requireNonNull(getActionBar()).hide();
        inicializarVariaveis();
        pontosJogador1 = getIntent().getIntArrayExtra("pontosJogador1");
        pontosJogador2 = getIntent().getIntArrayExtra("pontosJogador2");
        nomeJogador1 = getIntent().getStringExtra("nomeJogador1");
        nomeJogador2 = getIntent().getStringExtra("nomeJogador2");
        tvNome1.setText(nomeJogador1);
        tvNome2.setText(nomeJogador2);
        int pontuacao1 = 0;
        int pontuacao2 = 0;
        for (int i = 0; i < pontosJogador1.length; i++) {
            pontuacao1 += pontosJogador1[i];
        }
        for (int i = 0; i < pontosJogador2.length; i++) {
            pontuacao2 += pontosJogador2[i];
        }
        tvPontuacao1.setText(String.valueOf(pontuacao1) + " pontos");
        tvPontuacao2.setText(String.valueOf(pontuacao2) + " pontos");
        tvPontuacaoTotal.setText(String.valueOf(pontuacao1 + pontuacao2) + " pontos");
    }

    private void inicializarVariaveis() {
        tvNome1 = findViewById(R.id.tvNome1);
        tvNome2 = findViewById(R.id.tvNome2);
        tvPontuacao1 = findViewById(R.id.tvPontuacao1);
        tvPontuacao2 = findViewById(R.id.tvPontuacao2);
        tvPontuacaoTotal = findViewById(R.id.tvPontuacaoTotal);
    }
}
