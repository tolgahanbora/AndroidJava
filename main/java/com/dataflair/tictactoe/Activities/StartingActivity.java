package com.dataflair.tictactoe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dataflair.tictactoe.R;

public class StartingActivity extends AppCompatActivity {

    //Butona basıldığında oyuna başlar
    Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        getStartedBtn=(Button)findViewById(R.id.GetStartedBtn);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UsersActivity.class);
                startActivity(intent);
            }
        });
    }
}