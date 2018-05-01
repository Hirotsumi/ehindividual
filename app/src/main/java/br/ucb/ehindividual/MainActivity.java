package br.ucb.ehindividual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView imgGitHub;
    private ImageView imgHelp;
    private EditText editText;
    private Button btnContinuar;
    private TextView tvTitulo;
    private int cont;
    private String nomeJogador1;
    private String nomeJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getActionBar()).hide();
        inicializarVariaveis();
        btnContinuar.setOnClickListener(this);
        imgGitHub.setOnClickListener(this);
        imgHelp.setOnClickListener(this);
    }

    private void inicializarVariaveis() {
        editText = findViewById(R.id.editText);
        btnContinuar = findViewById(R.id.btnContinuar);
        tvTitulo = findViewById(R.id.tvTitulo);
        imgGitHub = findViewById(R.id.imgGitHub);
        imgHelp = findViewById(R.id.imgHelp);
        cont = 0;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgGitHub) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Hirotsumi/ehindividual")));
        }else if (view.getId() == R.id.imgHelp) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sobre o app");
            alert.setMessage("Este é um app de questões a respeito do MPS.BR. Vocês responderão individualmente 10 questões (valendo 10 pontos cada), em seguida será "
            + "possível revisá-las valendo a metade dos pontos. A ideia é que quem acertou a questão explique a quem errou para que a pontuação da dupla aumente! " +
             "Divirtam-se!");
            alert.setNeutralButton("OK", null);
            alert.show();
        }else{
            if (validarNome()) {
                cont++;
                switch (cont) {
                    case 0:
                        tvTitulo.setText("É individual?");
                        tvTitulo.setTextSize(50);
                        editText.setVisibility(View.GONE);
                        break;
                    case 1:
                        tvTitulo.setText("Nome do jogador 1");
                        tvTitulo.setTextSize(30);
                        editText.setText("");
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
                        intent.putExtra("nomeJogador1", nomeJogador1);
                        intent.putExtra("nomeJogador2", nomeJogador2);
                        startActivity(intent);
                        break;
                    default:
                        finish();
                }
            }else{
                Toast.makeText(this,"Informe o nome do jogador", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        cont-=2;
        editText.setText(" ");
        onClick(btnContinuar);
    }

    private boolean validarNome() {
        if (editText.getVisibility() == View.GONE)
            return true;
        if (editText.getText().toString().isEmpty())
            return false;
        return true;
    }
}
