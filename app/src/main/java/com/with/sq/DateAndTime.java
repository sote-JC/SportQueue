package com.with.sq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class DateAndTime extends AppCompatActivity {
    String timeTxt, calTxt;
    public static Context context_main;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime_main);
        context_main = this;

        Button compBtn = (Button) findViewById(R.id.compBtn);

        View header = getLayoutInflater().inflate(R.layout.selectoptions_main, null, false);
        TextView dtTxt = (TextView) header.findViewById(R.id.dateTxt);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        TimePicker time = (TimePicker) findViewById(R.id.timePick);

        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int h, int m) {
                timeTxt = h + "시 " + m + "분";
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                calTxt = year + "-" + (month + 1) + "-" + dayOfMonth;
            }
        });
        compBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectOptions.class);
                intent.putExtra("날짜", calTxt);
                intent.putExtra("시간", timeTxt);

                startActivity(intent);
            }
        });
    }
}
