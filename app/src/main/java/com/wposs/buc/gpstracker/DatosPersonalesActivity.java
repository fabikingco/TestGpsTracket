package com.wposs.buc.gpstracker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class DatosPersonalesActivity extends AppCompatActivity {

    String email, pass;
    EditText etNombre;
    Uri resultUri;
    CircularImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        etNombre = findViewById(R.id.etNombre);
        circularImageView = findViewById(R.id.circularImageView);

        Intent intent = getIntent();
        if (intent != null){
            email = intent.getStringExtra("email");
            pass = intent.getStringExtra("pass");
        }
    }

    public void crearUsuario(View view) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String fecha = format.format(date);

        Random r = new Random();
        int n = 100000 + r.nextInt(900000);
        String code = String.valueOf(n);

        if (resultUri != null){
            Intent myIntent = new Intent(this, InviteCodeActivity.class);
            myIntent.putExtra("email", email);
            myIntent.putExtra("pass", pass);
            myIntent.putExtra("name", etNombre.getText().toString());
            myIntent.putExtra("fecha", fecha);
            myIntent.putExtra("code", code);
            myIntent.putExtra("imageUri", resultUri);
            startActivity(myIntent);
            finish();

        } else {
            Toast.makeText(this, "Por favor selecciona una imagen", Toast.LENGTH_SHORT).show();
        }


    }

    public void selectImage (View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 12 && resultCode == RESULT_OK && data != null){
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(DatosPersonalesActivity.this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                resultUri = result.getUri();
                circularImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
    }
}
