package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Password_activity extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_activity);
        passwordEmail=(EditText)findViewById(R.id.etPasswordUpdate);
        resetPassword=(Button)findViewById(R.id.btButtonPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=passwordEmail.getText().toString().trim();
                if (useremail.equals("")){
                    Toast.makeText(Password_activity.this, "please enter email.",Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(Password_activity.this, "mail sent for reset password",Toast.LENGTH_SHORT).show();
                           finish();
                           startActivity(new Intent(Password_activity.this,MainActivity.class));
                       }     else {
                           Toast.makeText(Password_activity.this, "sorry error occur.",Toast.LENGTH_SHORT).show();
                       }
                        }
                    });
                }

            }
        });
    }
}
