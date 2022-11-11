package com.with.sq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectOptions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView sportTxt;
    String[] sports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectoptions_main);
        sports = new String[]{"축구", "농구", "배드민턴"};
        Spinner sport_spinner = (Spinner) findViewById(R.id.selectSport);
        sport_spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sport_spinner.setAdapter(adapter);
        sportTxt = (TextView)findViewById(R.id.sport);

        ImageButton imgBtn = (ImageButton) findViewById(R.id.profile);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

        Button calBtn = (Button) findViewById(R.id.selectDate);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Calendar.class);
                startActivity(intent);
            }
        });

        Button mapBtn = (Button) findViewById(R.id.selectLoc);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            }
        });

        Button retBtn = (Button) findViewById(R.id.turn);
        retBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectAction.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        sportTxt.setText(sports[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
        sportTxt.setText("스포츠");
    }
}
