package com.dragonfight.Arena;

import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;

public class Cell implements ICell{
    private final MsLocation mLocation;
    private CellType mType;
    private char mSymbol;
    private ICharacter mCharacter;
    private Arena mArena;

    public Cell(Arena arena,MsLocation location, CellType type){
        mArena = arena;
        mLocation = location;
        mType = type;
        mSymbol = type == CellType.Empty?' ':'#';
        mCharacter = null;
    }
    public Cell(Arena arena, MsLocation location, ICharacter character){
        mArena = arena;
        mLocation = location;
        mType = CellType.Character;
        mCharacter = character;
        mSymbol = mCharacter.toString().charAt(0);
    }

    public Arena getArena(){
        return mArena;
    }

    /**
     * @param mType the mType to set
     */
    @Override
    public void setType(CellType mType) {
        if(mCharacter != null)return;
        if(this.mType == mType)return;
        if(this.mType == CellType.StaticWall) return;
        if(mType == CellType.DynamicWall||mType == CellType.StaticWall)setSymbol('#');
        if(mType == CellType.Empty)setSymbol(' ');
        if(mType == CellType.Character)return;
        this.mType = mType;
    }/**
     * @return the mType
     */
    @Override
    public CellType getType() {
        return mType;
    }
    /**
     * @param mCharacter the mCharacter to set
     */
    @Override
    public void attachCharacter(ICharacter mCharacter) {
        
        if(mType!=CellType.Empty){
            System.out.println("Cell Already Occupied");
            return;
        }
        assert mCharacter.getCell() == null:"Char Cell Was Not Empty";
        this.mCharacter = mCharacter;
        mCharacter.setCell(this);
        this.mType = CellType.Character;
    }
    public void detachCharacter(){
        if(mCharacter==null) return;
        mCharacter.getCell().detachCharacter();
        mCharacter.setCell(null);
        mCharacter = null;
        setType(CellType.Empty);
    }
    /**
     * @return the mCharacter
     */
    @Override
    public ICharacter getCharacter() {
        return mCharacter;
    }
    /**
     * @param mSymbol the mSymbol to set
     */
    public void setSymbol(char mSymbol) {
        if(mType == CellType.StaticWall) return;

        this.mSymbol = mSymbol;
    }
    /**
     * @return the mSymbol
     */
    @Override
    public char getSymbol() {
        return mSymbol;
    }
    /**
     * @return the mLocation
     */
    @Override
    public MsLocation getLocation() {
        return mLocation;
    }

}