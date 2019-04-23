package com.nickpappas;

public class Cordinates {

    private int x;
    private int y;


    public Cordinates (int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cordinates (Cordinates cords){
        this.x = cords.getX();
        this.y = cords.getY();
    }

    public void setCordinates (Cordinates cords){
        this.x = cords.getX();
        this.y = cords.getY();
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}

