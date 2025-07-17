package com.tausif2606.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY ="com.tausif2606.twoactivities.MainActivity.EXTRA.MESSAGE" ;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage=findViewById(R.id.txtMessage);
    }

    public void StartSecondActivity(View view) {

        String msg =txtMessage.getText().toString();
        Intent intent =new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra(MESSAGE_KEY,msg);

        startActivity(intent);

    }
}