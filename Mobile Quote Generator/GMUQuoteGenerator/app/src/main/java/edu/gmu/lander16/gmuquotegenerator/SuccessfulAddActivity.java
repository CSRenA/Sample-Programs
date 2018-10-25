package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessfulAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_add);

        TextView userQuote = (TextView) findViewById(R.id.userQuoteSuccess);
        userQuote.setText(AddNewQuoteActivity.quoteText.getText().toString());
    }
}
