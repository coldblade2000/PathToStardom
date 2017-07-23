package com.example.idstudent.test;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameMenu extends AppCompatActivity implements View.OnClickListener, TextInputFragment.OnFragmentInteractionListener {
    ArrayList<ObjPlayer> players;
    LinearLayout[] layoutArray;
    Button bEndTurn,bAdd, bSubtract;
    TextView tvCurrentSubs, tvDeltaSubs, tvTurnCounter;
    String[] colors = {"#8803A9F4","#88FFC107","#882E7D32","#88F44336"};
    LinearLayout llPlayer1, llPlayer2, llPlayer3, llPlayer4, llTurn;
    FragmentTransaction ft;
    Fragment prev;
    int turn, currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        getSupportActionBar().setTitle("Path to Keemstardom");
        turn = 0;
        int numPlayers = getIntent().getIntExtra("playerNum",2);
        bEndTurn = (Button) findViewById(R.id.bEndTurn);
        bAdd = (Button) findViewById(R.id.bAdd);
        bSubtract = (Button) findViewById(R.id.bSubtract);
        bEndTurn.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bSubtract.setOnClickListener(this);

        tvCurrentSubs = (TextView) findViewById(R.id.tvCurrentSubs);
        tvDeltaSubs = (TextView) findViewById(R.id.tvDeltaSubs);
        tvTurnCounter = (TextView) findViewById(R.id.tvTurnCounter);

        llPlayer1 = (LinearLayout) findViewById(R.id.llPlayer1);
        llPlayer2 = (LinearLayout) findViewById(R.id.llPlayer2);
        llPlayer3 = (LinearLayout) findViewById(R.id.llPlayer3);
        llPlayer4 = (LinearLayout) findViewById(R.id.llPlayer4);
        layoutArray= new LinearLayout[4];

        llTurn = (LinearLayout) findViewById(R.id.llTurn);

        layoutArray[0] = llPlayer1;
        layoutArray[1] = llPlayer2;
        layoutArray[2] = llPlayer3;
        layoutArray[3] = llPlayer4;

        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i, new ObjPlayer(i));

        }
        for (int i = numPlayers; i < 4; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //layoutArray[i].setBackgroundColor(Color.parseColor("#cccccc"));
                layoutArray[i].setVisibility(View.INVISIBLE);
            }
        }
        for (int i=0; i<players.size();i++){
            layoutArray[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        startGame();
    }

    private void startGame(){
        turn = 1;
        currentPlayer=0;
        playerTurn(currentPlayer);
        tvDeltaSubs.setText(String.format("%s", (double) Math.round(calculateSubDelta(turn) * 100.0) / 100.0));
    }
    private void playerTurn(int player){
        currentPlayer=player;
       // tvCurrentSubs.setText(Math.round(players.get(player).getSubscribers())+"");
        tvCurrentSubs.setText((double) Math.round(players.get(player).getSubscribers() * 100.0) / 100.0+"");
        if(players.get(currentPlayer).getSubscribers()<0){
            tvCurrentSubs.setTextColor(Color.parseColor("#D50000"));
        }else{
            tvCurrentSubs.setTextColor(Color.parseColor("#00C853"));
        }
        for (int i=0;i<4;i++){
            if(i!=player){
                layoutArray[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
            }else{layoutArray[player].setBackgroundColor(Color.parseColor(colors[player]));}
        }
    }
    private double calculateSubDelta(int turn){
        /*
        o = (-0.2*input) * (0.2*input)
        i = o+6
        print( i)
*/
     return 6+((-0.2*turn)*(0.2*turn));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bEndTurn:
                if(currentPlayer==players.size()-1){
                    for (ObjPlayer a :
                            players) {
                        a.setSubscribers(a.getSubscribers()+calculateSubDelta(turn));
                    }
                    turn++;
                    tvTurnCounter.setText(turn+"");
                    tvDeltaSubs.setText(String.format("%s", (double) Math.round(calculateSubDelta(turn) * 100.0) / 100.0));
                    if(calculateSubDelta(turn)<0){
                        tvDeltaSubs.setTextColor(Color.parseColor("#D50000"));
                    }else{
                            tvDeltaSubs.setTextColor(Color.parseColor("#00C853"));
                    }
                    currentPlayer=0;
                    playerTurn(currentPlayer);

                }else{
                    currentPlayer++;
                    playerTurn(currentPlayer);
                }
                break;
            case R.id.bAdd:
                 ft = getSupportFragmentManager().beginTransaction();
                 prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                // Create and show the dialog.
                TextInputFragment newPositiveFragment = TextInputFragment.newInstance('+');
                newPositiveFragment.show(ft, "dialog");
                break;
            case R.id.bSubtract:
                 ft = getSupportFragmentManager().beginTransaction();
                 prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                // Create and show the dialog.
                TextInputFragment newNegativeFragment = TextInputFragment.newInstance('-');
                newNegativeFragment.show(ft, "dialog");
                break;
        }
    }

    @Override
    public void onFragmentInteraction(int amount) {
        players.get(currentPlayer).setSubscribers(players.get(currentPlayer).getSubscribers()+amount);
        Toast.makeText(this, "set amount of subs for Player "+(currentPlayer+1)+" to "+players.get(currentPlayer).getSubscribers(), Toast.LENGTH_SHORT).show();
        tvCurrentSubs.setText((double) Math.round(players.get(currentPlayer).getSubscribers() * 100.0) / 100.0+"");
    }
}
