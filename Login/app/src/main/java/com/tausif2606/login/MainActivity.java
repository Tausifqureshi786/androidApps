package com.tausif2606.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String SharedPrefFileName="com.tausif2606.login";
    private EditText user , pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=findViewById(R.id.userEditText);
        pass=findViewById(R.id.passwordEditText);

    }

    public void addANewUser(View view) {

        mPreferences=getSharedPreferences(SharedPrefFileName,MODE_PRIVATE);
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putString(user.getText().toString(), pass.getText().toString());
        preferenceEditor.apply();

        Toast.makeText(this, "User Addedd Successfully!", Toast.LENGTH_SHORT).show();

    }

    public void userLogin(View view) {
        mPreferences=getSharedPreferences(SharedPrefFileName,MODE_PRIVATE);

        String password = mPreferences.getString(user.getText().toString(),"");
        if(password.equals(pass.getText().toString()) )
        {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}