package edu.gmu.lander16.gmuquotegenerator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;


public class RandomQuoteActivity extends AppCompatActivity {


    final QuoteList ql = new QuoteList();
    ArrayList<Quotes> qtest = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quote);
        //get all quotes
        //String filepath = "/Users/landerson/Desktop/quotes.xml";
        final TextView quoteDisplay = (TextView) findViewById(R.id.randomQuote);

        InputStream is = this.getResources().openRawResource(R.raw.quotes);

        File path = this.getExternalFilesDir(null);
        File file = new File(path, "quotes.txt");
        if (file.length() == 0) {
            try {
                FileOutputStream os = new FileOutputStream(file);
                byte[] data = new byte[is.available()];
                is.read(data);
                os.write(data);
                is.close();
                os.close();

            } catch (IOException e) {
                //unable to create file, likely because external storage not available.
                Log.w("ExternalStorage", "Error writing" + file, e);
            }
        }

 /*       String[] str;
        int count = 1;

        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file1 = new File(sdcard, "quotes.txt");
            FileReader fr = new FileReader(file1);
            BufferedReader reader = new BufferedReader(fr);
            String s = reader.readLine();
            while (!s.equals("$")) {
                str = s.split("%", 2);
                if (str.length == 0) {
                    throw new Exception("THIS IS WRONG");
                }

                Quotes q = new Quotes(str[1], str[0]);
                qtest.add(q);
                s = reader.readLine();
                count++;
            }
            fr.close();
            reader.close();
            is.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        } */

        ActivityCompat.requestPermissions(RandomQuoteActivity.this , new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        onRequestPermissionsResult(1, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, new int[]{PackageManager.PERMISSION_GRANTED});

        ql.setQuotes(qtest);
        Quotes qt = ql.getRandomQuote();
        quoteDisplay.setText(qt.getQuoteText() + "\n\t -" + qt.getAuthor());

        //load a new quote if button is clicked
        final Button genQuote = (Button) findViewById(R.id.rqgen);
        genQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Quotes s = ql.getRandomQuote();
                quoteDisplay.setText(s.getQuoteText() + "\n\t -" + s.getAuthor());
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    String[] str;
                    int count = 1;

                    try {
                        File sdcard = Environment.getExternalStorageDirectory();
                        File file1 = new File(sdcard, "quotes.txt");
                        FileReader fr = new FileReader(file1);
                        BufferedReader reader = new BufferedReader(fr);
                        String s = reader.readLine();
                        while (!s.equals("$")) {
                            str = s.split("%", 3);
                            if (str.length == 0) {
                                throw new Exception("THIS IS WRONG");
                            }

                            Quotes q = new Quotes(str[1], str[0], str[2]);
                            qtest.add(q);
                            s = reader.readLine();
                            count++;
                        }
                        fr.close();
                        reader.close();

                    } catch (Exception e) {
                        System.out.println(e.toString());
                        return;
                    }

                }
            }
        }
    }
}
