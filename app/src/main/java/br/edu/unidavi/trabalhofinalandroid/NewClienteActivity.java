package br.edu.unidavi.trabalhofinalandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cliente);

        Button buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputNewTask = findViewById(R.id.input_new_task);
                String value = inputNewTask.getText().toString();

                if(!value.isEmpty()){
                    ClientesStore.getInstance(getApplicationContext())
                            .getClientesDao().insert(new Cliente(value));
                    finish();
                }
            }
        });
    }
}
