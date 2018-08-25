package com.dragonfight.Arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules{
    private Map<CellType,List<IRule>> rules;
    private IRule SurroundWithWalls = (cell)->{
        boolean x1 = cell.getLocation().getX() == 0;
        boolean x2 = cell.getLocation().getX() == cell.getArena().getWidth()-1;
        boolean y1 = cell.getLocation().getY() == 0;
        boolean y2 = cell.getLocation().getY() == cell.getArena().getHeight()-1;
        return x1||x2||y1||y2;
    };
    public Rules(){
        rules = new HashMap<>();
        
    }
    public void addRule(CellType type, IRule rule){
        rules.putIfAbsent(type, new ArrayList<IRule>());
        List<IRule> typeRules = rules.get(type);
        if(typeRules.contains(rule))return;
        typeRules.add(rule);        
    }
    public void removeRule( IRule rule){

    }

    public void tryApplyRules(ICell cell){
        if(rules.isEmpty()){
            addRule(CellType.StaticWall, SurroundWithWalls);
        }
        for (Map.Entry<CellType,List<IRule>> entry : rules.entrySet()) {
            for (IRule rule : entry.getValue()) {
                if(rule.check(cell)){
                    cell.setType(entry.getKey());
                }
            }
        }
    }
}