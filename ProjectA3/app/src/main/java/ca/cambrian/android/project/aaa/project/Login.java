package ca.cambrian.android.project.aaa.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText idText;
    EditText passwordText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, List.class);


                if ((idText.getText().toString().equalsIgnoreCase("Ellen")) && (passwordText.getText().toString().equals("111"))) {
                    Toast.makeText(Login.this, "Logging In", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else if ((idText.getText().toString().equalsIgnoreCase("Andy")) && (passwordText.getText().toString().equals("222"))) {
                    Toast.makeText(Login.this, "Logging In", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                else if ((idText.getText().toString().equalsIgnoreCase("Andrii")) && (passwordText.getText().toString().equals("333"))) {
                    Toast.makeText(Login.this, "Logging In", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                else {
                    Toast.makeText(Login.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
                }



            }

        });


        //intent


    }
}