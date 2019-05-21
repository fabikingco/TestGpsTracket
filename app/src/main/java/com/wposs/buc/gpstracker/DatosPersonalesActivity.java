package com.wposs.buc.gpstracker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class DatosPersonalesActivity extends AppCompatActivity {

    String user, pass;
    EditText etNombre;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        etNombre = findViewById(R.id.etNombre);

        Intent intent = getIntent();
        if (intent != null){
            user = intent.getStringExtra("user");
            pass = intent.getStringExtra("pass");
        }
    }

    public void crearUsuario(View view) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String fecha = format.format(date);

        String code = UUID.randomUUID().toString();

        if (resultUri != null){
            Intent myIntent = new Intent(this, InviteCodeActivity.class);
            myIntent.putExtra("user", user);
            myIntent.putExtra("pass", pass);
            myIntent.putExtra("name", etNombre.getText().toString());
            myIntent.putExtra("fecha", fecha);
            myIntent.putExtra("code", code);
            startActivity(myIntent);

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

        }
    }
}
