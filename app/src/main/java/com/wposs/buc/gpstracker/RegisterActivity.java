package com.wposs.buc.gpstracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegisterActivity extends AppCompatActivity {

    EditText etUser, etPass, etPassPass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        etPassPass = findViewById(R.id.etPassPass);

        auth = FirebaseAuth.getInstance();

    }

    public void registrar(View view) {
        auth.fetchSignInMethodsForEmail(etUser.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()){
                            boolean exist = !task.getResult().getSignInMethods().isEmpty();
                            if (!exist){
                                //Si el correo ingresado no ha sido usado puede registrarse
                                Intent intent = new Intent(RegisterActivity.this, DatosPersonalesActivity.class);
                                intent.putExtra("user", etUser.getText().toString());
                                intent.putExtra("pass", etPass.getText().toString());
                                startActivity(intent);

                            } else {
                                //El correo ya ha sido usado
                                Toast.makeText(RegisterActivity.this, "Este correo ya esta registrado", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}
