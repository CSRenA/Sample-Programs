package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UnsuccessfulAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsuccessful_add);


        TextView userQuote = (TextView) findViewById(R.id.userQuote);
        userQuote.setText(AddNewQuoteActivity.quoteText.getText().toString());
    }
}
