package br.edu.unidavi.trabalhofinalandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class CameraActivity extends AppCompatActivity {

    private Button buttonCamera;
    private ImageView imagemCapturada;
    private File imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        buttonCamera = findViewById(R.id.button_camera);
        imagemCapturada = findViewById(R.id.imagem_capturada);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo.jpg");

                Uri outputDir = FileProvider.getUriForFile(CameraActivity.this, BuildConfig.APPLICATION_ID, imagem);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputDir);

                startActivityForResult(intent, 1_000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1_000){
            if(data != null && data.hasExtra("data")){
                Bitmap thumbnail = data.getParcelableExtra("data");
                imagemCapturada.setImageBitmap(thumbnail);
            } else {
                int width = imagemCapturada.getWidth();
                int height = imagemCapturada.getHeight();

                BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
                factoryOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imagem.getPath(), factoryOptions);

                int imageWidth = factoryOptions.outWidth;
                int imageHeight = factoryOptions.outHeight;

                //Verificar o quanto precisamos escalar a imagem

                int scaleFactor = Math.min(imageWidth / width, imageHeight / height);

                factoryOptions.inJustDecodeBounds = false;
                factoryOptions.inSampleSize = scaleFactor;
                Bitmap image = BitmapFactory.decodeFile(imagem.getPath(), factoryOptions);
                imagemCapturada.setImageBitmap(image);
            }
        }
    }
}
