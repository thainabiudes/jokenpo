package br.com.up.aula02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.up.aula02.entity.Possiblity;
import br.com.up.aula02.enums.OptionsGame;

public class ResultActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private List<Possiblity> possibilities = new ArrayList<>();

    private static final String RADIO_BUTTON_KEY = "key";
    private static final String USER_NAME = "name";
    private static final String DRAW_MESSAGE = "Ocorreu um empate, %s. Jogue novamente 🤞 \n O computador escolheu: %s";
    private static final String WIN_MESSAGE = "Parabéns %s, você ganhou 🎉 \n O computador escolheu: %s";
    private static final String LOSE_MESSAGE = "Oops, %s, infelizmente você perdeu 😞 \n O computador escolheu: %s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.populatePossibilities();

        OptionsGame valueUser = OptionsGame.valueOf(this.getParameters(RADIO_BUTTON_KEY));
        String name = this.getParameters(USER_NAME);
        OptionsGame valueComputer = OptionsGame.getRandomOptionsGame();

        textView = findViewById(R.id.resultado);

        if (valueUser == valueComputer) {
            textView.setText(String.format(DRAW_MESSAGE, name, valueComputer.toString()));
        }
        else {
            if (this.validateLose(valueUser, valueComputer)) {
                textView.setText(String.format(LOSE_MESSAGE, name, valueComputer.toString()));
            }
            else {
                textView.setText(String.format(WIN_MESSAGE, name, valueComputer.toString()));
            }
        }

        button = findViewById(R.id.button_reiniciar_partida);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                finish();
                Intent intentResult = new Intent();
                setResult(RESULT_OK, intentResult); //declara que funcionamento foi ok
                finish();
            }
        });


    }

    private boolean validateLose(OptionsGame userOption, OptionsGame computerOption) {
        Possiblity option = this.possibilities.stream().filter(possiblity -> possiblity.getCurrentOption() == userOption).findAny().orElse(null);
        return option.getWinOption() == computerOption ? true : false;
    }

    private void populatePossibilities() {
        this.possibilities.add(new Possiblity(OptionsGame.PEDRA, OptionsGame.PAPEL));
        this.possibilities.add(new Possiblity(OptionsGame.TESOURA, OptionsGame.PEDRA));
        this.possibilities.add(new Possiblity(OptionsGame.PAPEL, OptionsGame.TESOURA));
    }

    private String getParameters(String nameParameter) {
        Bundle extras = getIntent().getExtras();
        String value = null;

        if (extras != null) {
            value = extras.getString(nameParameter);
        }

        return value;
    }
}