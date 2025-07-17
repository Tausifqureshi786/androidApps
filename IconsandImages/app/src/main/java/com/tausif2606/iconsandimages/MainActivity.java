package com.tausif2606.iconsandimages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int count=0;

    public void changeImage(View view)
    {
        ImageView iv = findViewById(R.id.imgAnimal);
        iv.setAlpha(0.0f);
        iv.setTranslationY(-1500.0f);
        iv.setScaleX(1.0f);
        iv.setScaleY(1.0f);
        if(count%2==0)
        {

            iv.setImageResource(R.drawable.dog);
//            iv.animate().alpha(1.0f).setDuration(2000);
            iv.animate()
                    .rotationBy(3600.0f)
                    .translationYBy(1500.0f)
                    .scaleX(2.0f)
                    .scaleY(2.0f)
                    .alpha(1.0f)
                    .setDuration(2000);
        }
        else
        {
            iv.setImageResource(R.drawable.cat);
            iv.animate().rotationBy(3600.0f)
                    .translationYBy(1500.0f)
                    .alpha(1.0f)
                    .setDuration(2000);


        }


        count++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}