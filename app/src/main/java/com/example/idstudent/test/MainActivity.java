package com.example.idstudent.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etPlayerNum;
    Button bPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPlayerNum = (EditText) findViewById(R.id.etPlayerNum);
        bPlay = (Button) findViewById(R.id.bPlay);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = 0;
                value = Integer.parseInt(etPlayerNum.getText().toString());
                if (value < 2 || value > 4){
                    Toast.makeText(getApplicationContext(), "Sorry, please input a valid amount of players", Toast.LENGTH_SHORT).show();
                }else if (value >= 2 || value <=4){
                    Intent intent = new Intent(getApplicationContext(), GameMenu.class);
                    intent.putExtra("playerNum", value);
                    startActivity(intent);
                }
            }
        });
    }
}
