package br.ucb.ehindividual;

import android.app.Activity;
import android.os.Bundle;

import java.util.Objects;

public class RevisarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        Objects.requireNonNull(getActionBar()).hide();
    }
}
