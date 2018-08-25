package com.dragonfight.Arena;

import java.util.HashMap;
import java.util.Map;

import com.dragonfight.Misc.MsLocation;

public class Arena {
    private static int nameAdder = 0;
    private String mName;
    private int mWidth;
    private int mHeight;
    private ICell[][] mArena;

    public Arena(int width, int height){
        this(width,height,"Arena"+(++nameAdder));
    }

    public Arena(int width, int height, String name) {
        mWidth = width;
        mHeight = height;
        mArena = new ICell[height][width];
        mName = name;
        ++nameAdder;
    }
    public void initializeAreana(Rules rules){
        for(int y = 0; y < mHeight; y++){
            for(int x = 0; x< mWidth; x++){
                ICell cell = new Cell(this,new MsLocation(x,y),CellType.Empty);
                rules.tryApplyRules(cell);
                mArena[y][x] = cell;
            }
        }
    }
    public void initializeAreana(){
        initializeAreana(new Rules());
    }


    public boolean inArena(MsLocation location) {
        boolean x = location.getX() < mWidth && location.getX() >= 0;
        boolean y = location.getY() < mHeight && location.getY() >= 0;
        return x && y;
    }
    public ICell cellAt(MsLocation location){
        if(!inArena(location))return null;
        return mArena[location.getY()][location.getX()];
    }

    public String printArena() {
        String arenaStr = "";
        for (int y = 0; y < mHeight; y++) {
            arenaStr += " ";
            for (int x = 0; x < mWidth; x++) {
                arenaStr += mArena[y][x].getSymbol();
            }
        }
        return arenaStr;
    }

    /**
     * @return the mHeight
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * @return the mWidth
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * @param mHeight the mHeight to set
     */
    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    /**
     * @param mWidth the mWidth to set
     */
    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public ICell[][] getArena(){
        return mArena;
    }

    public String getName(){
        return mName;
    }
}