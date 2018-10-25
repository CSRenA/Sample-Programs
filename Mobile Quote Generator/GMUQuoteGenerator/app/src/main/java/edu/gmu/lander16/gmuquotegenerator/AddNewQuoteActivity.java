package edu.gmu.lander16.gmuquotegenerator;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddNewQuoteActivity extends AppCompatActivity {
    static EditText quoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_quote);


        final EditText text = (EditText) findViewById(R.id.quoteText);
        final EditText author = (EditText)findViewById(R.id.quoteAuthor);
        final EditText keyword = (EditText)findViewById(R.id.keyword);
        quoteText = text;

        final Button submitQuote = (Button) findViewById(R.id.submitQuote);
        submitQuote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                File path = Environment.getExternalStorageDirectory();
                File file = new File(path, "quotes.txt");

                //if "oldquotes.txt" exists, delete it.
                File old = new File(path, "oldquotes.txt");
                boolean deleted = old.delete();


                try {
                    final String data = text.getText().toString() + "%" + author.getText().toString() + "%" + keyword.getText().toString();

                    //Check if user input contains illegal characters
                    if (data.contains("[") || data.contains("$") || data.contains("{") || keyword.getText().toString().contains("%") || author.getText().toString().contains("%") || text.getText().toString().contains("%") || data.contains(">") || data.contains("$") || text.getText().toString().matches("") || author.getText().toString().matches("") || keyword.toString().matches("")) {

                        //Add unsuccessful. Go to add unsuccessful activity.
                        Intent intent2 = new Intent(view.getContext(), UnsuccessfulAddActivity.class);
                        view.getContext().startActivity(intent2);


                    }
                    else {

                        String fileClose = "$";
                        Scanner scanner = new Scanner(file);       // create scanner to read
                        File newFile = new File(path, "newquotes.txt");
                        PrintWriter writer = new PrintWriter(newFile);
                        //FileOutputStream os = new FileOutputStream(file);
                        //os.write(data.getBytes());
                        //os.write(fileClose.getBytes());
                        // os.close();

                        //write to new file
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals("$")) {
                                line = "";
                            }
                            writer.println(line);
                        }
                        //now add new data and the file closer
                        writer.println(data);
                        writer.println(fileClose);
                        writer.close();
                        scanner.close();
                        //now rename files so that quotes points to the file with the new line added.

                        File oldQuotes = new File(path, "oldquotes.txt");
                        file.renameTo(oldQuotes);
                        newFile.renameTo(file);

                        Intent intent = new Intent(view.getContext(), SuccessfulAddActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }

                catch(Exception e){
                    System.out.print(e.toString());
                    return;
                }


            }
        });
    }
}
