package com.wposs.buc.gpstracker;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class InviteCodeActivity extends AppCompatActivity {

    String email, pass, name, fecha, code, imageUri;
    TextView textView, textView2;

    FirebaseAuth authFirebase;
    FirebaseUser userFirebase;
    DatabaseReference referenceFirebase;
    AlertDialog dialog;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);

        dialog = new SpotsDialog(this);

        authFirebase = FirebaseAuth.getInstance();
        referenceFirebase = FirebaseDatabase.getInstance().getReference().child("Users");

        textView = findViewById(R.id.textView);


        Intent myIntent = getIntent();
        if (myIntent != null){
            name = myIntent.getStringExtra("name");
            email = myIntent.getStringExtra("email");
            pass = myIntent.getStringExtra("pass");
            fecha = myIntent.getStringExtra("fecha");
            code = myIntent.getStringExtra("code");
            imageUri = myIntent.getStringExtra("imageUri");
            textView.setText(code);
        }
    }

    public void registerUser(View view) {

        dialog.show();

        authFirebase.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Insertar valores en la base de datos
                            CreateUser createUser = new CreateUser(name, email, pass, code, "false", "na", "na", "na");
                            userFirebase = authFirebase.getCurrentUser();
                            userId = userFirebase.getUid();

                            referenceFirebase.child(userId).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                dialog.dismiss();
                                                Toast.makeText(InviteCodeActivity.this, "Usuarios registrado exitosamente", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(InviteCodeActivity.this, UserLocationMainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                dialog.dismiss();
                                                Toast.makeText(InviteCodeActivity.this, "No se puedo crear el usuario. ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });



    }
}
