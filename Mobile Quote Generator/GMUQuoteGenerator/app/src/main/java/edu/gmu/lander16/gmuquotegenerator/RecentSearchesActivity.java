package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.TextView;

public class RecentSearchesActivity extends AppCompatActivity {
    TextView t;
    //list of all searches. Populated by SearchQuotesActivity.
    static ArrayList<String> searches = new ArrayList<String>();
    String listOfQuotes = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_searches);
        int count = 1;
        //add the numbered search terms to the string
            for (String term : searches) {
                listOfQuotes += (count + ". " + term + "\n");
                count++;
            }
            //put string in text view.
            t = (TextView) findViewById(R.id.searchedText);
            t.setText(listOfQuotes);

    }
}
