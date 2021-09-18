package br.com.up.aula02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//jokenpo
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddLink;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText result;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        View fabAddLink = findViewById(R.id.button_proximo);

        fabAddLink.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                if(radioButton == null) {
                    Toast.makeText(MainActivity.this,
                            "Por favor, selecione sua opção de jogo", Toast.LENGTH_LONG).show();
                } else {
                    callRegisterActivity(radioButton.getTag().toString());
                }
            }

            private void callRegisterActivity(String string){
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text

                                        if (userInput.getText().toString().isEmpty()) {
                                            Toast.makeText(MainActivity.this,
                                                    "Por favor, digite seu nome para continuar!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Intent i = new Intent(MainActivity.this, ResultActivity.class);
                                            i.putExtra("key", string);
                                            i.putExtra("name", userInput.getText().toString());
                                            startActivityForResult(i, 1000); //aguarda resultado
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1000){
            radioGroup.clearCheck();
        }
    }

}