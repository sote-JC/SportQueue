package com.with.sq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Context context_main;
    public String nickname;
    private UserDao userDao;
    boolean isCorrect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context_main = this;
        EditText id = (EditText) findViewById(R.id.ID);
        EditText pw = (EditText) findViewById(R.id.PW);
        UserDB userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, "SportQueue_userdb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        userDao = userDB.userDao();
        List<User> userList = userDao.getUserAll();

        Button signUpBtn = (Button) findViewById(R.id.signUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for (int i = 0; i < userList.size(); i++) {
                    if(id.getText().toString().equals(userList.get(i).getId())) {
                        if(pw.getText().toString().equals(userList.get(i).getPassword())) {
                            Intent intent = new Intent(getApplicationContext(), Profile.class);
                            nickname = userList.get(i).getNickname();
                            startActivity(intent);
                            isCorrect = true;
                            break;
                        }
                    }
                }
                if(!isCorrect){
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}