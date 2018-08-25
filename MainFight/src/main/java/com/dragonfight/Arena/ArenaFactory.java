package com.dragonfight.Arena;

import java.util.ArrayList;
import java.util.List;


public class ArenaFactory{
    
    private static Arena mArena;
    private static List<Arena> mArenas;
    public enum Type{
        Single,
        Multiple
    }


    public static Arena CreateArena(int width, int height){
        mArena = new Arena(width, height);
        mArena.initializeAreana();
        return mArena;
    }
    public static Arena CreateArena(int width, int height, Rules rules){
        mArena = new Arena(width, height);
        mArena.initializeAreana(rules);
        return mArena;
    }
    public static Arena CreateArena(int width, int height,Type type){
        if(type == Type.Single||type == null){
            return CreateArena(width, height);
            
        }
        Arena arena =  new Arena(width, height);
        arena.initializeAreana();
        if(mArenas == null)mArenas = new ArrayList<>();
        mArenas.add(arena);
        return arena;
    }
    public static Arena CreateArena(int width, int height, Rules rules, Type type){
        if(type == Type.Single||type == null){
            return CreateArena(width, height,rules);
        }
        Arena arena =  new Arena(width, height);
        arena.initializeAreana(rules);
        if(mArenas == null)mArenas = new ArrayList<>();
        mArenas.add(arena);
        return arena;
    }
    
    public static Arena getArena(String arenaName){
        if(mArenas == null||mArenas.isEmpty())return mArena;
        Arena arena = mArenas.stream().filter(ar->ar.getName() == arenaName).findFirst().orElse(null);
        return arena;
    }
    public static List<Arena> getAllArenas(){
        return mArenas;
    }
    
}