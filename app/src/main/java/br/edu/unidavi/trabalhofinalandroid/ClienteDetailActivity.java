package br.edu.unidavi.trabalhofinalandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ClienteDetailActivity extends AppCompatActivity {

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detail);

        int id = getIntent().getIntExtra("id", 0);
        cliente = ClientesStore.getInstance(this).getClientesDao().findById(id);
        setTitle(cliente.getNome());

        //Exclui o cliente
        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientesStore.getInstance(getApplicationContext())
                        .getClientesDao()
                        .delete(cliente);
                finish();
            }
        });
    }
}
