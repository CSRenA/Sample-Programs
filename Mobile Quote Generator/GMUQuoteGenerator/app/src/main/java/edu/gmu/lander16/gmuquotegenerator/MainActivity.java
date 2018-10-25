package edu.gmu.lander16.gmuquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //this class allows you to choose which activity you are going to do

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View a random quote
        final Button rqotd = (Button) findViewById(R.id.rqotd);
        rqotd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RandomQuoteActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //Search Quotes
        final Button sq = (Button) findViewById(R.id.sq);
        sq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchQuotesActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //View search activity
        final Button rs = (Button) findViewById(R.id.rs);
        rs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecentSearchesActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        //View add quote activity

        final Button anq = (Button) findViewById(R.id.anq);
        anq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNewQuoteActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        final Button keyword = (Button) findViewById(R.id.keywordSearch);
        keyword.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchKeywordActivity.class);
                view.getContext().startActivity(intent);
            }
            });

    }
}
