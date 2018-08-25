package com.dragonfight.weapons.types;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;
import com.dragonfight.weapons.Weapon;

public class LongRangWeapon extends Weapon{



    public LongRangWeapon(ICharacter owner, String name, int minDamage, int maxDamage, int offset) {
        super(owner, name, minDamage, maxDamage, offset);
        mConditionMsg = "Enemy Too Far, Can't Attack At This Range";
    }

	@Override
	public boolean Condition() {
        if (getCharacter().getCurrentEnemy() == null)
            return false;
        Arena ar = getArena();
        ICharacter owner = getCharacter();
        ICharacter enemy = getCharacter().getCurrentEnemy();

        int distance = owner.getLocation().distance(enemy.getLocation());
        MsLocation zeroLoc = new MsLocation(0,0);
        MsLocation biggestLoc = new MsLocation(ar.getWidth(), ar.getHeight());
        int bigDistance = zeroLoc.distance(biggestLoc);
        double ofstPrc = (distance/bigDistance)*100;
        setOffset((int)ofstPrc);
        return true;
	}
}