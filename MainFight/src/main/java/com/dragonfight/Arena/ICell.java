package com.dragonfight.Arena;

import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;

public interface ICell{


    Arena getArena();

    void setType(CellType mType);
    CellType getType();

    void attachCharacter(ICharacter mCharacter);
    void detachCharacter();
    ICharacter getCharacter();

    void setSymbol(char mSymbol);
    char getSymbol();

    MsLocation getLocation();
}