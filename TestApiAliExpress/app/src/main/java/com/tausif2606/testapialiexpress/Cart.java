package com.tausif2606.testapialiexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.tausif2606.testapialiexpress.Models.EventModel;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ArrayList<Parcelable> model= getIntent().getParcelableArrayListExtra("model");

        EventModel imp = (EventModel) model.get(0);


    }
}