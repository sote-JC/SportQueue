package com.with.sq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class SignUp extends AppCompatActivity {
    private UserDao userDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.signup_main);
        EditText nickname = (EditText) findViewById(R.id.nickname);
        EditText id = (EditText) findViewById(R.id.newID);
        EditText pw = (EditText) findViewById(R.id.newPW);
        EditText checkPw = (EditText) findViewById(R.id.checkPW);

        UserDB userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, "SportQueue_userdb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        userDao = userDB.userDao();
        Button signUpBtn = (Button) findViewById(R.id.signingUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pw.getText().toString().equals(checkPw.getText().toString())) {
                    User user = new User();
                    user.setNickname(nickname.getText().toString());
                    user.setId(id.getText().toString());
                    user.setPassword(pw.getText().toString());
                    userDao.setInsertUser(user);

                    Toast.makeText(getApplicationContext(), "가입이 완료되었습니다!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "가입 정보를 다시 확인해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
