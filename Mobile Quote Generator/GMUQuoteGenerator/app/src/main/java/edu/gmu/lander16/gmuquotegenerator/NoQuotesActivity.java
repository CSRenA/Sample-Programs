package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class NoQuotesActivity extends AppCompatActivity {
//this activity displays when there are no quotes.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_quotes);

        TextView tv = (TextView) findViewById(R.id.searchedTerm);
            tv.setText(SearchQuotesActivity.searchTerm.getText().toString());
    }
}
