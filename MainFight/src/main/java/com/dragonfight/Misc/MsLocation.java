package com.dragonfight.Misc;

public class MsLocation{
    private int mX;
    private int mY;

    public MsLocation(int x, int y){
        mX = x;
        mY = y;
    }


    public int distance(MsLocation other){
        double dx = Math.pow(this.mX- other.getX(),2);
        double dy = Math.pow(this.mY- other.getY(),2);
        return (int)Math.sqrt(dx+dy);
    }








    /**
     * @return the mX
     */
    public int getX() {
        return mX;
    }
    /**
     * @return the mY
     */
    public int getY() {
        return mY;
    }/**
     * @param mX the mX to set
     */
    public void setX(int mX) {
        this.mX = mX;
    }/**
     * @param mY the mY to set
     */
    public void setY(int mY) {
        this.mY = mY;
    }
}