package com.example.hp_pc.niyoktahr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by hp-pc on 8/5/2018.
 */

public class LoginActivity extends CommonClass {

    private EditText name;
    private EditText password;
    private Button login;
    private TextView userReg, forgetPassword;
    private ProgressDialog mProgressDialog;


    SignInButton mButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        initialization();


    }






    private void initialization(){

        name = (EditText) findViewById(R.id.etname);
        forgetPassword = (TextView) findViewById(R.id.tvforgetPass);

        password = (EditText) findViewById(R.id.etpassword);
        login = (Button) findViewById(R.id.etlogin);
        userReg = (TextView) findViewById(R.id.tvSignup);
        mButton = (SignInButton) findViewById(R.id.googlebtn);

        mProgressDialog = new ProgressDialog(this);

        userReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Password_activity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("")) {
                    Toast.makeText(LoginActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    validate(name.getText().toString(), password.getText().toString());
                }
            }
        });

        Log.e(" here second come s ", " here");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                  // startActivity(new Intent(LoginActivity.this, employee_or_employer.class));
                    CheckingEmployeePersonalInfo();
                    finish();
                }
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(LoginActivity.this, "auth went wrong", Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        //
                        //
                        // ...
                    }
                });


    }

    private void validate(final String userName, String userPassword) {
        if (userName.equals("")) {
            Toast.makeText(LoginActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
        } else {
            mProgressDialog.setMessage("Please Wait");
            mProgressDialog.show();
            mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        emailVeification();

                        //startActivity(new Intent(MainActivity.this,Secondpage.class));
                    } else {


                        Toast.makeText(LoginActivity.this, "sorry login Failed.", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("Here request code", requestCode +"    ll");
        Log.e("Here result code", resultCode +"    ll");
        Log.e("Here data code", data +"    ll");
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately

                Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

                // ...
            }
        }
    }

    private void emailVeification() {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        if (emailFlag) {

            finish();

            mProgressDialog.dismiss();
           // startActivity(new Intent(LoginActivity.this, employee_or_employer.class));
            CheckingEmployeePersonalInfo();
            finish();
        } else {

            Toast.makeText(LoginActivity.this, "please verify email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onBackPressed() {


      finishAffinity();

    }
}
