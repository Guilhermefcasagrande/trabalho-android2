package br.edu.unidavi.trabalhofinalandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button buttonCamera;
    private Button buttonMapa;
    private File imagem;
    private Button buttonPermisao;
    private Button buttonActivityClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = findViewById(R.id.button_camera);
        buttonMapa = findViewById(R.id.button_mapa);
        buttonPermisao = findViewById(R.id.button_permissao);
        buttonActivityClientes = findViewById(R.id.button_activity_clientes);

        buttonActivityClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ClientesActivity.class));
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CameraActivity.class));
            }
        });

        buttonPermisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCameraPermission()) {
                    return;
                }
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Permissão necessária!")
                            .setPositiveButton("OK", null)
                            .show();
                }
                requestPermission();
            }
        });


    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                1000);
    }

    boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this,
                        "Permissão Negada",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Permissão Concedida!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
