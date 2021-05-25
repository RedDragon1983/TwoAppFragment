package com.test.twoappfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddUserActivity extends AppCompatActivity {
    Button insertUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    Boolean newUser;
    TextView textView;
    Button delUserBtn;
    User user;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        insertUserBtn = findViewById(R.id.insertUserBtn);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        textView = findViewById(R.id.textView);
        delUserBtn = findViewById(R.id.delUserBtn);
        users = Users.get(this);

        //Users users = Users.get(AddUserActivity.this);

        Intent intent = getIntent();
        int userData = intent.getIntExtra("userdata",-1); // получаем переданную строку
        newUser = (userData == -1); // если новый пользователь то -1, иначе номер пользователя

        if(newUser){
            insertUserBtn.setText("Добавить пользователя");
            textView.setText("Добавить");
            delUserBtn.setVisibility(View.GONE);
        }else {



            user = users.getUserList().get(userData);
            editTextName.setText(user.getUserName());
            editTextLastName.setText(user.getUserLastName());
            editTextPhone.setText(user.getPhone());
            textView.setText("Редактирование пользователя");
            insertUserBtn.setText("Редактировать");
        }

        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newUser) {
                    user = new User();
                    user.setUserName(editTextName.getText().toString());
                    user.setUserLastName(editTextLastName.getText().toString());
                    user.setPhone(editTextPhone.getText().toString());
                    users.addUser(user);
                } else{
                    user.setUserName(editTextName.getText().toString());
                    user.setUserLastName(editTextLastName.getText().toString());
                    user.setPhone(editTextPhone.getText().toString());
                    users.updateUser(user);
                }
                onBackPressed();
            }
        });

        delUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.delUser(user.getUuid().toString());
                onBackPressed();
            }
        });
    }
}