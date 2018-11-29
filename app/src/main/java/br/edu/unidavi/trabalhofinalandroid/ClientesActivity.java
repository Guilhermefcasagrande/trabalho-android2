package br.edu.unidavi.trabalhofinalandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    private ClienteAdapter adapter = new ClienteAdapter(new ClienteAdapter.OnClienteClickListener() {
        @Override
        public void onClick(Cliente cliente) {
            Intent intent = new Intent(getApplicationContext(), ClienteDetailActivity.class);
            intent.putExtra("id", cliente.getId());
            startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        RecyclerView tasklist = findViewById(R.id.cliente_list);
        tasklist.setLayoutManager(new LinearLayoutManager(this));
        tasklist.setAdapter(adapter);

        FloatingActionButton buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Quando clicar no botão de adicionar, joga pra outra tela
                startActivity(new Intent(getApplicationContext(), NewClienteActivity.class));
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Cliente> tasks = ClientesStore.getInstance(this).getClientesDao().fetchClientes();
        adapter.setUp(tasks);
    }
}
