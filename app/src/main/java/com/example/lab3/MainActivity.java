package com.example.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.lab3.R.id.email;

public class MainActivity extends AppCompatActivity {

    EditText pass;
    EditText pass2;
    EditText phone;
    EditText email;
    EditText f_name;
    EditText l_name;
    TextView res;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       pass = (EditText) findViewById(R.id.password);
       pass2 = (EditText) findViewById(R.id.confirm);
       phone = (EditText) findViewById(R.id.phone);
       email = (EditText) findViewById(R.id.email);
       f_name = (EditText) findViewById(R.id.first_name);
       l_name = (EditText) findViewById(R.id.last_name);
       res = (TextView)findViewById(R.id.result);

        Button btn_submit = (Button)findViewById(R.id.btn_submit);

        flag = true;



        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                res.setText("");

                CheckFields();
                CheckPassword();
                CheckEmail();

                if(flag == true) {
                    res.setText("Authorization is successful");
                }
            }
        });
    }

    public void CheckPassword(){
        if(pass.getText().toString().equals(pass2.getText().toString()) == false) {
            res.setText(res.getText().toString() + "\n The password do not match");
            flag = false;
        }

        if(pass.getText().toString().trim().length() < 8 || pass2.getText().toString().trim().length() < 8) {
            res.setText(res.getText().toString() + "\n Short password");
            flag = false;
        }
    }

    public void CheckEmail(){
        if (email.getText().toString().trim().matches(emailPattern) == false) {
            res.setText(res.getText().toString() + "\n The email is not valid");
            flag = false;
        }
    }

    public void CheckFields(){
        if(pass.getText().toString().trim().length() == 0 || pass2.getText().toString().trim().length() == 0 || phone.getText().toString().trim().length() == 0
                || email.getText().toString().trim().length() == 0 || f_name.getText().toString().trim().length() == 0 || l_name.getText().toString().trim().length() == 0) {
            res.setText(res.getText().toString() + "\n Enter all fields");
            flag = false;
        }
    }
}
