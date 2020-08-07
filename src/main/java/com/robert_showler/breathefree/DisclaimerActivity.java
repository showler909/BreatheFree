package com.robert_showler.breathefree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DisclaimerActivity extends AppCompatActivity {

    private Button buttonConsent;
    private Button buttonNoConsent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        buttonConsent = findViewById(R.id.buttonConsent);
        buttonNoConsent = findViewById(R.id.buttonNoConsent);

        buttonConsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity1();
            }
        });

        buttonNoConsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity2();
            }
        });
    }

    public void startActivity1() {

        // if already login
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));
    }


    public void startActivity2() {
            startActivity(new Intent(this, IntroActivity.class));
    }

}





