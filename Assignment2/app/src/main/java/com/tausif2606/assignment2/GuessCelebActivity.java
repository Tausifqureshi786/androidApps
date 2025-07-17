package com.tausif2606.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessCelebActivity extends AppCompatActivity {



    Bitmap image;
    ArrayList celebrityNames = new ArrayList<String>();
    ArrayList celebrityPictureUrls=new ArrayList<String>();

    ImageView imageView;

    int chosenCeleb =0;
    int locationOfCorrectAnswer =0;
    String[]answers = new String[4];

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void celebChosen(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Toast.makeText(getApplicationContext(),"Correct!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Wrong! It was "+ celebrityNames.get(chosenCeleb).toString(), Toast.LENGTH_LONG).show();
        }

        createNewQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_celeb);

        imageView=findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button4);
        button3 = (Button) findViewById(R.id.button5);
        extractStrings();
        createNewQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.ActionAbout:
                Intent intent = new Intent(GuessCelebActivity.this, Activity_About.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;
            InputStream is;

            try {
                url=new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                is=urlConnection.getInputStream();
                image= BitmapFactory.decodeStream(is);
                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class downloadWebsiteHTML extends AsyncTask<String, Void, String> {
        //don't forget to use <uses-permission android:name="android.permission.INTERNET" />
        // in the android manifests
        String result;
        InputStream is;
        URL url;
        HttpURLConnection urlConnection;
        InputStreamReader reader;
        @Override
        protected String doInBackground(String... urls) {
            result="";
            try {
                url=new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    result += inputLine;
                }
                br.close();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return result;
        }
    }

    public void extractStrings() {


        String webAddress="https://www.fandango.com/famous-actors-and-actresses";
        //String webAddress="https://www.usatoday.com/story/sports/nfl-100/2019/10/01/nfl-100-best-players-all-time/3785514002/";
        GuessCelebActivity.downloadWebsiteHTML dtask=new GuessCelebActivity.downloadWebsiteHTML();
        String htmlStr;




        try {
            htmlStr=dtask.execute(webAddress).get();

            //learn REGEX expressions from here:
            //https://www.tutorialspoint.com/javaregex/javaregex_characters.htm

            //<h2 class=gnt_ar_b_h2><strong>1: Jerry Rice</strong></h2><figure class="gnt_em gnt_em_img">
            // <img class=gnt_em_img_i style=height:362px data-g-r=lazy data-gl-src=
            // "https://www.gannett-cdn.com/presto/2019/10/23/USAT/850ca6f5-faab-4d8e-85bf-8fcfbe059843-XXX_49ERS_-_RAMS.JPG?crop=1525,836,x18,y0&width=660&height=362&format=pjpg&auto=webp" data-gl-srcset

            //"tag\\](.+?)\\[/tag"
            //Pattern p=Pattern.compile("strong\\>\\d*:(.*?)\\</strong");
            //Pattern p=Pattern.compile("https://(.*?)crop");
            /*
            Pattern p=Pattern.compile("strong\\>\\d*:(.*?)\\</strong(.*?)https://(.*?)crop");
            Matcher m=p.matcher(htmlStr);
            while(m.find())
            {
                if(m.group(1).length()>=2&&m.group(1).length()<30) {
                    celebrityNames.add(m.group(1));
                    celebrityPictureUrls.add(m.group(3));
                }
            }*/

            //<img id="GlobalBody_PerformersRepeater_PerformerImage_0"
            // src="https://images.fandango.com/r1.0.798/ImageRenderer/120/180/redesign/static/img/noxportrait.jpg/p302167/cp/cpc/images/masterrepository/performer%20images/p302167/chrisevans12.jpg"
            // alt="Chris Evans" />


            //website address
            //webAddress="https://www.fandango.com/famous-actors-and-actresses";
            Pattern p=Pattern.compile("src=\"(.*?)\" alt=\"(.*?)\" />");
            Matcher m=p.matcher(htmlStr);
            while(m.find())
            {
                if(m.group(2).length()>=2&&m.group(2).length()<30) {
                    celebrityNames.add(m.group(2));
                    celebrityPictureUrls.add(m.group(1));
                    //break;
                }
            }
            Log.i("ABCDEF","ABCDEF");
            Log.i("Celebrity Name: ", celebrityNames.toString());
            Log.i("Celebrity Pic Url: ", celebrityPictureUrls.toString());


//            Intent intent = new Intent(this, LoadHTML.class);
//            intent.putExtra("Name", celebrityNames.toString());
//            intent.putExtra("URL", celebrityPictureUrls.toString());


            // startActivity(intent);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createNewQuestion(){
        Random random = new Random();

        chosenCeleb = random.nextInt(celebrityPictureUrls.size());

        GuessCelebActivity.DownloadImage task=new GuessCelebActivity.DownloadImage();
        Bitmap downloadedImage;

        try {
            downloadedImage=task.execute(celebrityPictureUrls.get(chosenCeleb).toString()).get();
            imageView.setImageBitmap(downloadedImage);

            locationOfCorrectAnswer = random.nextInt(4);

            int incorrectAnswerLocation;
            for(int i=0;i<4;i++)
            {
                if(i == locationOfCorrectAnswer){

                    answers[i] = celebrityNames.get(chosenCeleb).toString();
                }
                else{
                    incorrectAnswerLocation =random.nextInt(celebrityPictureUrls.size());

                    while(incorrectAnswerLocation == chosenCeleb)
                    {
                        incorrectAnswerLocation =random.nextInt(celebrityPictureUrls.size());
                    }

                    answers[i] = celebrityNames.get(incorrectAnswerLocation).toString();
                }
            }

            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}