package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DetailsUserActivity extends AppCompatActivity {
    Button contact;
    EditText phoneNumber;
    Button callButton;
    Button BackHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);

        BackHome = findViewById(R.id.button8);
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsUserActivity.this, SuiteActivity.class);
                startActivity(intent);
            }
        });


        callButton = findViewById(R.id.button7);
        phoneNumber = findViewById(R.id.editTextText);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumbers = phoneNumber.getText().toString();

                if (ActivityCompat.checkSelfPermission(DetailsUserActivity.this, "android.permission.CALL_PHONE") != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailsUserActivity.this, new String[]{"android.permission.CALL_PHONE"}, 123);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumbers));
                    startActivity(callIntent);
                }
            }
        });


        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.email_dialog, null);

        final EditText TOEditText = dialogView.findViewById(R.id.TO);
        final EditText subjectEditText = dialogView.findViewById(R.id.subjectEditText);
        final EditText bodyEditText = dialogView.findViewById(R.id.bodyEditText);

        contact = findViewById(R.id.button5);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsUserActivity.this);

                Button sendButton = dialogView.findViewById(R.id.sendButton);

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();

                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendEmail();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
            private void sendEmail()
            {

                String to = TOEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String body = bodyEditText.getText().toString();

                String[] adresses = to.split(",");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:$adresses")); // Only email apps should handle this
                emailIntent.putExtra(Intent.EXTRA_EMAIL, adresses);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                emailIntent.setType("body/rfc822"); //for typing only email

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent.createChooser(emailIntent,"Choose an email client "));
                }else{
                    Toast.makeText(DetailsUserActivity.this, "Aucune applicaiton choisie", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
