package com.with.sq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends AppCompatActivity {
    public static Context context_profile;
    private UserDao userDao;
    Uri uri;
    Bitmap bitmap;
    ImageView profileImg;
    byte[] byteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);
        context_profile = this;
        TextView nick = (TextView) findViewById(R.id.nick);
        nick.setText(((MainActivity)MainActivity.context_main).nickname);

        UserDB userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, "SportQueue_userdb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        userDao = userDB.userDao();

        profileImg = (ImageView) findViewById(R.id.profile);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });

        EditText intro = (EditText) findViewById(R.id.introduce);

        if(userDao.getUserIntroduce(nick.getText().toString()) != null){
            intro.setText(userDao.getUserIntroduce(nick.getText().toString()));
        }

        Button compBtn = (Button) findViewById(R.id.compBtn);
        compBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.updateIntroduce(intro.getText().toString(), ((MainActivity)MainActivity.context_main).nickname);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                float scale = (float) (1024 / (float)bitmap.getWidth());

                int image_w = (int)(bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), SelectAction.class);
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){

                        uri = result.getData().getData();

                        try{
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            profileImg.setImageBitmap(bitmap);
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
}
