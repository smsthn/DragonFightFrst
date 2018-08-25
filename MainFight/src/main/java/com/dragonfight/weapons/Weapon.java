package com.dragonfight.weapons;

import com.dragonfight.Character.ICharacter;

import jdk.jfr.Percentage;

public abstract class Weapon implements IWeapon{
	private static int idCounter;
    private final int Id;
    private String mName;
    protected final int MinDamage;
    protected final int MaxDamage;
    private int Offset;
	private ICharacter mOwner;
	protected String mConditionMsg;


	public Weapon(ICharacter owner, String name, int minDamage, int maxDamage, int offset){
		Id = ++idCounter;
		mName = name;
		MinDamage = minDamage;
		MaxDamage = maxDamage;
		setOffset(offset);
		mOwner = owner;
	}




	@Override
	public int getId() {
		return Id;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public int getDamage(){
		return (int)((Math.random()*(MaxDamage - MinDamage)+MinDamage))-Offset;
	}

	@Override
	public void setOffset(int offsetPercentag) {
        if(offsetPercentag <= 0)Offset = 0;
		else if(offsetPercentag >= 100) {Offset= MaxDamage - MinDamage;}
		
        else {Offset = (MaxDamage - MinDamage)*(offsetPercentag/100);}
	}

	@Override
	public ICharacter getCharacter() {
		return mOwner;
	}

	@Override
	public void setCharacter(ICharacter character) {
		mOwner = character;
	}

	@Override
	public abstract boolean Condition();

	@Override
	public String getCondtitionMsg(){
		return mConditionMsg;
	}



}