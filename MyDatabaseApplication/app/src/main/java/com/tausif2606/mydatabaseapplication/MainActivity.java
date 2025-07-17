package com.tausif2606.mydatabaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public SQLiteDatabase db;

    public void createDatabase(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS MyTable(name VARCHAR , age INTEGER(3))");

            db.execSQL("INSERT INTO MyTable(name, age) VALUES('Mahbub Murshed', 40)");
            db.execSQL("INSERT INTO MyTable(name, age) VALUES('Alex', 30)");
            db.execSQL("INSERT INTO MyTable(name, age) VALUES('Tausif', 20)");
            db.execSQL("INSERT INTO MyTable(name, age) VALUES('David', 25)");
            db.execSQL("INSERT INTO MyTable(name, age) VALUES('Amin', 45)");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }

    public void loadTableData(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS MyTable(name VARCHAR , age INTEGER(3))");

            Cursor query= db.rawQuery("SELECT * FROM MyTable", null);

            int nameIndex=query.getColumnIndex("name");
            int ageIndex=query.getColumnIndex("age");

            query.moveToFirst();
            while(query!=null)
            {
//                Toast.makeText(this, query.getString(nameIndex), Toast.LENGTH_SHORT).show();
                Log.i("MyTable:","Name"+query.getString(nameIndex) + ", Age: "+query.getString(ageIndex) );
                query.moveToNext();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }



    public void deleteATableRecord(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
            db.execSQL("DELETE FROM MyTable WHERE name='Mahbub Murshed'");

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }


    public void createTable(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS BookInfo(id INTEGER PRIMARY KEY, bookId INTEGER(5), title VARCHAR, author VARCHAR, pages INTEGER(4))");

            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 100,'Android Applications', 'Tausif Qureshi', 70)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 200,'Mobile Application Development', 'Mo Qureshi', 1000)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 300,'Android Fundamentals', 'Supreet Singh', 550)");

            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 400,'Let us C++', 'Kanithkar', 200)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 500,'Algorithms and Datastructures', 'Coreman', 700)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 600,'Computer Graphics', 'Edward Angel', 500)");

            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 700,'Microprocessors', 'Barry B. Bray', 2500)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 800,'Systems Analysis and Design', 'Alan', 1300)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 900,'Operating System', 'Galvin', 350)");

            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 100,'Database System', 'Korth', 650)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 200,'Compiler Design', 'Allen', 150)");
            db.execSQL("INSERT INTO BookInfo(bookId, title, author, pages) VALUES( 300,'Networking Fundamentals', 'Richard', 400)");


        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }

    public void loadBookTableData(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);

            // only for iterating through the database table
            Cursor query= db.rawQuery("SELECT * FROM BookInfo", null);

            int idIndex=query.getColumnIndex("id");
            int bookIdIndex=query.getColumnIndex("bookId");
            int titleIndex=query.getColumnIndex("title");
            int authorIndex=query.getColumnIndex("author");
            int pagesIndex=query.getColumnIndex("pages");


            query.moveToFirst();
            while(query!=null)
            {
//                Toast.makeText(this, query.getString(nameIndex), Toast.LENGTH_SHORT).show();
                Log.i("BookInfo:","Id: "+query.getInt(idIndex) + ", Book ID: "+query.getInt(bookIdIndex)+
                        ", Title: "+query.getString(titleIndex) + ", Author: "+query.getString(authorIndex)
                        + ", Pages: "+query.getInt(pagesIndex));
                query.moveToNext();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }


    public void deleteARecordFromBookTable(){

        try{
            db=this.openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
            db.execSQL("DELETE FROM BookInfo ");

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        db.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        createDatabase();
        //deleteATableRecord();
//        loadTableData();

//        createTable();
//        deleteARecordFromBookTable();
        loadBookTableData();
//        deleteARecordFromBookTable();
//        deleteARecordFromBookTable();

    }
}