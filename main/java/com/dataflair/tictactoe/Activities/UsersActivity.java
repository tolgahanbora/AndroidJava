package com.dataflair.tictactoe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dataflair.tictactoe.MainActivity;
import com.dataflair.tictactoe.R;

public class UsersActivity extends AppCompatActivity {

    Button singlePlayerBtn, multiPlayerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        singlePlayerBtn = (Button) findViewById(R.id.SinglePlayerBtn);
        multiPlayerBtn = (Button) findViewById(R.id.MultiPlayerBtn);


        //Butona basıldığında OnClicklistener event oluşturur ve tek oyunculu oyuna girmeyi sağlar
        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
                startActivity(intent);
            }
        });

        //Onclicklistener ile event oluşturdum 2 kişilik oyun oynanmasını sağladım
        multiPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}