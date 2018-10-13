package com.example.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    SharedPreferences shared;
    ArrayList<String> name;
    ArrayList<String> number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        shared = getSharedPreferences("users_list", MODE_PRIVATE);

        name = new ArrayList<>();
        number = new ArrayList<>();

        pass = (EditText) findViewById(R.id.password);
        pass2 = (EditText) findViewById(R.id.confirm);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        f_name = (EditText) findViewById(R.id.first_name);
        l_name = (EditText) findViewById(R.id.last_name);
        res = (TextView)findViewById(R.id.result);

        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        Button btn_view = (Button)findViewById(R.id.view_list);

        flag = true;



        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag = true;
                res.setText("");

                CheckFields();
                CheckPassword();
                CheckEmail();
                if(flag == true) {
                    res.setText("Authorization is successful");

                    try {
                        retriveSharedValue();
                    } catch (Exception e){}

                    name.add(f_name.getText().toString() + ' ' + l_name.getText().toString());
                    number.add(phone.getText().toString());
                    Clear();
                    packagesharedPreferences();
                }
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
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
    public void Clear(){
        pass.setText("");
        pass2.setText("");
        email.setText("");
        phone.setText("");
        f_name.setText("");
        l_name.setText("");
    }

    private void packagesharedPreferences() {
        SharedPreferences.Editor editor = shared.edit();
        Set<String> set_name = new HashSet<String>();
        Set<String> set_number = new HashSet<String>();
        set_name.addAll(name);
        set_number.addAll(number);
        editor.putStringSet("name", set_name);
        editor.putStringSet("number", set_number);
        editor.apply();
        Log.d("storesharedPreferences",""+set_name);
        Log.d("storesharedPreferences",""+set_number);
    }

    private void retriveSharedValue() {
        Set<String> set_name = shared.getStringSet("name", null);
        name.addAll(set_name);
        Set<String> set_number = shared.getStringSet("number", null);
        number.addAll(set_number);

        Log.d("resharedPreferences",""+set_name);
        Log.d("resharedPreferences",""+set_number);
    }
}