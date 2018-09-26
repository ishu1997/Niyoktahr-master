package com.example.hp_pc.niyoktahr;

/**
 * Created by hp-pc on 9/18/2018.
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class employer_verification extends AppCompatActivity {
    Button generate_code;
    String name , phone_number,location,email_from,website;
    EditText verification_code;
    LinearLayout verification_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_verification);

        generate_code = (Button) findViewById(R.id.generate_code_employer);
        verification_layout = (LinearLayout) findViewById(R.id.verification_layout);
        verification_code = (EditText) findViewById(R.id.verification_code);

        get_intent();



        generate_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                verification_layout.setVisibility(View.VISIBLE);
                generate_code.setVisibility(View.GONE);



                //  ------code for email sent--------


                    Log.i("Send email", "");

                    String[] TO = {"niyoktahr@gmail.com"};
                    String[] CC = {"niyoktahr@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");


                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Employer NyoktaHR solutions. pvt. ltd");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Name - " + name + "\n" + "Phone - " + phone_number + "\n" + "location - " + location+ "\n" + "email - " + email_from+ "\nwebsite - " + website);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                        //  Log.i("Finished sending email...", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(),
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }



            }



        });




    }



    public void verfication_done(View view){

        String code = verification_code.getText().toString();
        String final_code = getResources().getString(R.string.verif_code);

        if( code.equals(final_code)){
            Toast.makeText(employer_verification.this , "SUCCESS" ,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,job_posting.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(employer_verification.this , "Enter correct code" ,Toast.LENGTH_SHORT).show();
        }

    }


    public void Resend_done(View view){
        generate_code.setVisibility(View.VISIBLE);
        verification_layout.setVisibility(View.GONE);


    }


    public void get_intent(){
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        phone_number = intent.getStringExtra("PHONE");
        location = intent.getStringExtra("LOCATION");
        email_from = intent.getStringExtra("Email");
        website = intent.getStringExtra("website");


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
