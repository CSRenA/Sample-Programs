package edu.gmu.lander16.gmuquotegenerator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class SearchQuotesActivity extends AppCompatActivity {
    //this activity uses the given search term to search through all of the known files.
    //since SAX parser not working, use txt file to create QuoteList
    int mode;
    static QuoteList foundQuotes;
    static EditText searchTerm;
    final QuoteList ql = new QuoteList();

    ArrayList<Quotes> qtest = new ArrayList<>();


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_quotes);


        InputStream is = this.getResources().openRawResource(R.raw.quotes);
        //create file to be stored in app data so quotes are permanent.

        File path = this.getExternalFilesDir(null);
        File file = new File(path, "quotes.txt");
        if(file.length() == 0) {
            try {
                FileOutputStream os = new FileOutputStream(file);
                byte[] data = new byte[is.available()];
                is.read(data);
                os.write(data);
                is.close();
                os.close();

            } catch (IOException e) {
                //unable to create file, likely because external storage not available.
                Log.w("ExternalStorage", "Error writing--LAUERN" + file, e);
            }
        }

        String[] str;
        int count = 1;
        //Create QuoteList from quotes found in external storage file
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file1 = new File(sdcard,"quotes.txt");
            FileReader fr = new FileReader(file1);
            BufferedReader reader = new BufferedReader(fr);
            String s = reader.readLine();
            while (!s.equals("$")){
                str = s.split("%", 3);
                if(str.length == 0){
                    throw new Exception("THIS IS WRONG");
                }

                Quotes q = new Quotes(str[1],str[0],str[2]);
                qtest.add(q);
                s = reader.readLine();
                count++;
            }
            fr.close();
            reader.close();
            is.close();

        }
        catch(Exception e) {
            System.out.println("OH NO");
            return;
        }

        ql.setQuotes(qtest);
        final Button search = (Button) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RadioButton quotes = (RadioButton) findViewById(R.id.Quote);
                RadioButton author = (RadioButton) findViewById(R.id.Author);
                RadioButton both = (RadioButton) findViewById(R.id.Both);
                //check which method of searching we are doing
                if(author.isChecked()){ mode = 0;}
                else if(quotes.isChecked()){ mode = 1;}
                else{ mode = 2;}
                final EditText searchCriteria = (EditText) findViewById(R.id.search);
                //make the term visible to the NoQuotesActivity class
                searchTerm = searchCriteria;
                final QuoteList searchedQuotes = ql.search(searchCriteria.getText().toString(),mode);
                RecentSearchesActivity.searches.add(searchCriteria.getText().toString());
                //if there are found quotes, display them
                if(searchedQuotes.getSize()!=0) {
                    //make the quotes available to the FoundQuotesActivity
                    foundQuotes = searchedQuotes;
                    Intent intent = new Intent(view.getContext(), FoundQuotesActivity.class);
                    view.getContext().startActivity(intent);
                }
                //if there are no quotes, let the user know
                else{
                    Intent intent = new Intent(view.getContext(), NoQuotesActivity.class);
                    view.getContext().startActivity(intent);
                }


            }
        });
    }

}
