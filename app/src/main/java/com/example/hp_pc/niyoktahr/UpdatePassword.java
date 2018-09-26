package com.example.hp_pc.niyoktahr;

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
import com.google.firebase.auth.FirebaseUser;

public class UpdatePassword extends AppCompatActivity {
    private Button update;
    private EditText mEditText;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
         update=(Button)findViewById(R.id.btupdatePassword);
        mEditText=(EditText)findViewById(R.id.etPasswordUpdate);
        mFirebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String passwordnew=mEditText.getText().toString();
                mFirebaseUser.updatePassword(passwordnew).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdatePassword.this,"password change",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(UpdatePassword.this,"password  not change",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });



    }
}
