package com.with.sq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectAction extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectaction_main);

        TextView nicknameTxt = (TextView) findViewById(R.id.nickname);
        nicknameTxt.setText(((MainActivity)MainActivity.context_main).nickname);

        Button selectMatch = (Button) findViewById(R.id.selectMatch);
        selectMatch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SelectOptions.class);
                startActivity(intent);
            }
        });

        ImageView img = (ImageView) findViewById(R.id.profile);
        byte[] byteArray = ((Profile)Profile.context_profile).byteArray;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img.setImageBitmap(bitmap);
    }
}
