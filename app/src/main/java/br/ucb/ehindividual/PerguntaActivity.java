package br.ucb.ehindividual;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class PerguntaActivity extends Activity {

    TextView tvNomeJogador1;
    TextView tvNomeJogador2;
    String nomeJogador1;
    String nomeJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        Objects.requireNonNull(getActionBar()).hide();
        inicializarVariaveis();
        nomeJogador1 = getIntent().getStringExtra("nomeJogador1");
        nomeJogador2 = getIntent().getStringExtra("nomeJogador2");
        tvNomeJogador1.setText(nomeJogador1);
        tvNomeJogador2.setText(nomeJogador2);
    }

    private void inicializarVariaveis() {
        tvNomeJogador1 = findViewById(R.id.tvNomeJogador1);
        tvNomeJogador2 = findViewById(R.id.tvNomeJogador2);
    }
}
