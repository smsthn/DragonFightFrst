package com.dragonfight.Arena;

import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;

@FunctionalInterface
public interface IRule{
    boolean check(ICell cell);
}