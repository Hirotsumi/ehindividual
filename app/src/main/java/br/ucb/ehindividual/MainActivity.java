package br.ucb.ehindividual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText editText;
    Button btnContinuar;
    TextView tvTitulo;
    int cont;
    String nomeJogador1;
    String nomeJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVariaveis();
        btnContinuar.setOnClickListener(this);
    }

    private void inicializarVariaveis() {
        editText = findViewById(R.id.editText);
        btnContinuar = findViewById(R.id.btnContinuar);
        tvTitulo = findViewById(R.id.tvTitulo);
        cont = 0;
    }

    @Override
    public void onClick(View view) {
        cont++;
        switch (cont) {
            case 1:
                tvTitulo.setText("Nome do jogador 1");
                tvTitulo.setTextSize(30);
                editText.setVisibility(View.VISIBLE);
                btnContinuar.setText("Continuar");
                break;
            case 2:
                nomeJogador1 = editText.getText().toString();
                editText.setText("");
                tvTitulo.setText("Nome do jogador 2");
                btnContinuar.setText("COMEÇAR!");
                break;
            case 3:
                nomeJogador2 = editText.getText().toString();
                finish();
                Intent intent = new Intent(this, PerguntaActivity.class);
                intent.putExtra("nomeJogador1",nomeJogador1);
                intent.putExtra("nomeJogador2",nomeJogador2);
                startActivity(intent);
                break;
            default:
                finish();
        }
    }

    @Override
    public void onBackPressed() {
        cont-=2;
        onClick(btnContinuar);
    }
}
