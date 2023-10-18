package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SuiteActivity extends AppCompatActivity {
    private TextView t;

    Button MoreDetails;

    Button googleIt;
    Button Logout;
    EditText id;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suite);

        t = findViewById(R.id.textView3);
        Intent intent = getIntent();
        String s = intent.getStringExtra("login");
        t.setText("L'utilisateur connecté est "+s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        MoreDetails = findViewById(R.id.button2);
        MoreDetails.setText("Plus de details sur "+s);

        MoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuiteActivity.this, DetailsUserActivity.class);
                startActivity(intent);
            }
        });

        Logout = findViewById(R.id.button3);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuiteActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        googleIt = findViewById(R.id.button4);

        googleIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

}