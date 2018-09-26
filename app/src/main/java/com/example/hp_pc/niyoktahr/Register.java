package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText userName,userPassword,userEmail;
    private Button regButton;
    private TextView regText;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName=(EditText)findViewById(R.id.etregName);
        userPassword=(EditText)findViewById(R.id.etregPass);
        userEmail=(EditText)findViewById(R.id.etemail);
        regButton=(Button)findViewById(R.id.regButton);
        regText=(TextView)findViewById(R.id.tvReg);
        firebaseAuth=FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (validate()){
                  String user_email=userEmail.getText().toString().trim();
                  String user_password=userPassword.getText().toString().trim();
                  firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           sentEmailVerification();
                           //Toast.makeText(Register.this,"register succesful",Toast.LENGTH_SHORT).show();
                           //startActivity(new Intent(Register.this,MainActivity.class));
                       }
                          else {
                           Toast.makeText(Register.this,"register failed",Toast.LENGTH_SHORT).show();
                       }
                      }
                  });
              }
            }
        });
        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });
    }

    private Boolean validate(){
        Boolean result=false;
        String name=userName.getText().toString();
        String password=userPassword.getText().toString();
        String email=userEmail.getText().toString();

        if (name.isEmpty() &&password.isEmpty()&&email.isEmpty()){
            Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show();

        }
        else {
            result=true;
        }
        return result;
    }
    private void sentEmailVerification(){
        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "register succesfull,email verification sent to your mail.",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }else {
                        Toast.makeText(Register.this, " email Failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
