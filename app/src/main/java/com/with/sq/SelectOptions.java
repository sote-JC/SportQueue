package com.with.sq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.w3c.dom.Text;

public class SelectOptions extends AppCompatActivity{
    ArrayAdapter<CharSequence> sport, position;
    TextView sportTxt, positionTxt, locTxt, dtTxt;
    Spinner sport_spinner, position_spinner;
    String data;
    private MatchDao matchDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectoptions_main);

        TextView nick = (TextView) findViewById(R.id.nickname);
        nick.setText(((MainActivity)MainActivity.context_main).nickname);

        sport_spinner = (Spinner) findViewById(R.id.selectSport);
        position_spinner = (Spinner) findViewById(R.id.selectPosition);
        sportTxt = (TextView) findViewById(R.id.sport);
        positionTxt = (TextView) findViewById(R.id.position);

        sport = ArrayAdapter.createFromResource(this, R.array.spinner_sport, android.R.layout.simple_spinner_dropdown_item);
        sport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sport_spinner.setAdapter(sport);
        sport_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sport.getItem(i).equals("축구")) {
                    sportTxt.setText("축구");
                    position = ArrayAdapter.createFromResource(SelectOptions.this, R.array.spinner_soccer, android.R.layout.simple_spinner_dropdown_item);
                    position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    position_spinner.setAdapter(position);
                    position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            positionTxt.setText(position.getItem(i).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (sport.getItem(i).equals("농구")) {
                    sportTxt.setText("농구");
                    position = ArrayAdapter.createFromResource(SelectOptions.this, R.array.spinner_basketball, android.R.layout.simple_spinner_dropdown_item);
                    position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    position_spinner.setAdapter(position);
                    position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            positionTxt.setText(position.getItem(i).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (sport.getItem(i).equals("배구")) {
                    sportTxt.setText("배구");
                    position = ArrayAdapter.createFromResource(SelectOptions.this, R.array.spinner_volleyball, android.R.layout.simple_spinner_dropdown_item);
                    position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    position_spinner.setAdapter(position);
                    position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            positionTxt.setText(position.getItem(i).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (sport.getItem(i).equals("배드민턴")) {
                    sportTxt.setText("배드민턴");
                    position = ArrayAdapter.createFromResource(SelectOptions.this, R.array.spinner_badminton, android.R.layout.simple_spinner_dropdown_item);
                    position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    position_spinner.setAdapter(position);
                    position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            positionTxt.setText(position.getItem(i).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button calBtn = (Button) findViewById(R.id.selectDate);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DateAndTime.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String calTxt = intent.getStringExtra("날짜");
        String timeTxt = intent.getStringExtra("시간");
        if(calTxt != null && timeTxt != null) {
            dtTxt = (TextView) findViewById(R.id.dateTxt);
            dtTxt.setText(calTxt + " " + timeTxt);
        }

        String location = intent.getStringExtra("위치");
        if(location != null){
            TextView locTxt = (TextView) findViewById(R.id.locTxt);
            locTxt.setText(location);
        }

        locTxt = (TextView) findViewById(R.id.locTxt);
        Button selectLoc = (Button) findViewById(R.id.selectLoc);
        selectLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectOptions.this, SearchAPI.class);
                getSearchResult.launch(intent);
            }
        });
        EditText playTimeTxt = (EditText) findViewById(R.id.playTime);

        MatchDB matchDB = Room.databaseBuilder(getApplicationContext(), MatchDB.class, "SportQueue_matchdb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        matchDao = matchDB.matchDao();
        Match match = new Match();

        Button matching = (Button)findViewById(R.id.matching);
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match.setNickname(((MainActivity)MainActivity.context_main).nickname);
                match.setSport(sport_spinner.getSelectedItem().toString());
                match.setPosition(position_spinner.getSelectedItem().toString());
                match.setDate(dtTxt.getText().toString());
                match.setLocation(locTxt.getText().toString());
                match.setPlaytime(Integer.parseInt(playTimeTxt.getText().toString()));
                matchDao.insertMatch(match);
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectAction.class);
                startActivity(intent);
            }
        });
    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    if(result.getData() != null){
                        data = result.getData().getStringExtra("data");
                        locTxt.setText(data);
                    }
                }
            }
    );
}
