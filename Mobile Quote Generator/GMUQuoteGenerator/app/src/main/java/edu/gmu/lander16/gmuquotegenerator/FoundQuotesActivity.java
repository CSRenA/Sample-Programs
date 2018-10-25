package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoundQuotesActivity extends AppCompatActivity {
    //this class displays all the quotes found with the search term

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_quotes);

        TextView tv = (TextView) findViewById(R.id.foundQuotes);
        //turn the quotes into a long string that can be displayed.
        String quotes = "";
            for (Quotes quote : SearchQuotesActivity.foundQuotes.getQuotes()) {
                quotes += quote.getQuoteText();
                quotes += "\n\t -";
                quotes += quote.getAuthor();
                quotes += "\n";
                SearchQuotesActivity.foundQuotes = null;
            //add quotes to the view
            tv.setText(quotes);
        }
    }
}
