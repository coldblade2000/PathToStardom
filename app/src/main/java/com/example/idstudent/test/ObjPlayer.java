package com.example.idstudent.test;



public class ObjPlayer {
    public int playerNum; //This number goes from 0 to 3, but it shoiuld be increased by 1 when displayed
    public String name;
    public double subscribers;

    public ObjPlayer(int index){
        this.playerNum = index;
        this.subscribers= 5;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(double subscribers) {
        this.subscribers = subscribers;
    }
    public int getPlayerNum(){
        return playerNum;
    }
    public int getDisplayPlayerNum(){
        return playerNum+1;
    }

}
