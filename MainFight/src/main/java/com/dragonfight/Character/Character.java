package com.dragonfight.Character;

import java.util.List;

import com.dragonfight.Arena.CellType;
import com.dragonfight.Arena.ICell;
import com.dragonfight.Character.CharHelperClasses.CharacterData;
import com.dragonfight.Character.CharHelperClasses.Inventory;
import com.dragonfight.Misc.MsLocation;
import com.dragonfight.weapons.IWeapon;

public class Character implements ICharacter {

	private CharacterData mData;
	private Inventory mInventory;
	private ICell mCurrentCell;

	public Character() {
		mData = new CharacterData();
		mInventory = new Inventory();
	}

	public Character(CharacterData data) {
		mData = data;
		mInventory = new Inventory();
		
	}

	public Character(CharacterData data, Inventory inventory) {
		mData = data;
		mInventory = inventory;
		
	}

	@Override
	public int getId() {
		return mData.id;
	}

	@Override
	public String getName() {
		return mData.name;
	}

	@Override
	public void setName(String name) {
		mData.name = name;
	}

	@Override
	public char getSymbol() {
		return mData.symbol;
	}

	@Override
	public void setSymbol(char Symbol) {
		mData.symbol = Symbol;
	}

	@Override
	public CharacterType getCharacterType() {
		return mData.type;
	}

	@Override
	public void setCharacterType(CharacterType type) {
		mData.type = type;
	}

	@Override
	public ICell getCell() {
		return mCurrentCell;
	}

	@Override
	public void setCell(ICell cell) {
		if (cell.getType() != CellType.Empty) {
			System.out.println("Cell Already Occupied C");
			return;
		}
		
		if(mCurrentCell != null)mCurrentCell.detachCharacter();
		mCurrentCell = cell;
		mCurrentCell.attachCharacter(this);
	}

	@Override
	public int getHp() {
		return mData.hp;
	}

	@Override
	public void takeDamage(int takeDamage) {
		mData.hp -= takeDamage;
	}

	@Override
	public boolean isAlive() {
		return mData.hp > 0;
	}

	@Override
	public IWeapon getWeapon(String weaponName) {
		if (mInventory.weapons.isEmpty()) {
			System.out.println("Inventory Is Empty");
			return null;
		}
		IWeapon weapon = mInventory.weapons.stream().filter((wpn) -> wpn.getName() == weaponName).findFirst().orElse(null);
		return mInventory.currentWeapon = weapon != null?weapon: mInventory.currentWeapon;
	}

	@Override
	public IWeapon getWeapon(int WeaponIndex) {
		if (mInventory.weapons.isEmpty()) {
			System.out.println("Inventory Is Empty");
			return null;
		}
		if (WeaponIndex >= mInventory.weapons.size())WeaponIndex = mInventory.weapons.size() - 1;
		if(WeaponIndex< 0 )WeaponIndex = 0;
		return mInventory.currentWeapon =  mInventory.weapons.get(WeaponIndex);
	}

	@Override
	public IWeapon getRandomWeapon() {
		if (mInventory.weapons.isEmpty()) {
			System.out.println("Inventory Is Empty");
			return null;
		}
		return mInventory.currentWeapon =  mInventory.weapons.get((int)(Math.random()*mInventory.weapons.size()));
	}

	@Override
	public IWeapon addWeapon(IWeapon weapon) {
		if(weaponExists(weapon))return null;
		mInventory.weapons.add(weapon);
		return mInventory.currentWeapon = weapon;
		
	}

	@Override
	public List<IWeapon> getAllWeapons() {
		return mInventory.weapons;
	}

	@Override
	public boolean weaponExists(String name) {
		return mInventory.weapons.stream().anyMatch((wpn)->wpn.getName() == name);
	}

	@Override
	public boolean weaponExists(IWeapon weapon) {
		return mInventory.weapons.stream().anyMatch((wpn)->wpn.getName() == weapon.getName());
	}

	@Override
	public IWeapon removeWeapon(String name) {
		if(!weaponExists(name))return null;
		IWeapon wpn = getWeapon(name);
		mInventory.weapons.remove(wpn);
		return wpn;
	}

	@Override
	public IWeapon removeWeapon(IWeapon weapon) {
		if(!weaponExists(weapon))return null;
		IWeapon wpn = mInventory.weapons.stream().filter(pn->pn.getId()==weapon.getId()).findFirst().orElse(null);
		mInventory.weapons.remove(wpn);
		return wpn;
	}

	@Override
	public void attack(IWeapon weapon) {
		if(getCurrentEnemy() == null)return;
		getCurrentEnemy().takeDamage(weapon.getDamage());
	}

	@Override
	public void attack(IWeapon weapon,ICharacter enemy) {
		enemy.takeDamage(weapon.getDamage());
	}
	
	@Override
	public ICharacter getCurrentEnemy() {
		return mData.currentEnemy;
	}

	@Override
	public ICharacter setCurrentEnemy(ICharacter enemy) {
		if(!mData.allEnemies.contains(enemy))mData.allEnemies.add(enemy);
		return  mData.currentEnemy = enemy;
	}

	@Override
	public List<ICharacter> getAllEmemies() {
		return mData.allEnemies;
	}
	@Override
	public List<ICharacter> getAllAllies() {
		return mData.allAllies;
	}

	@Override
	public void addEnemy(ICharacter character) {
		if(!mData.allEnemies.contains(character))mData.allEnemies.add(character);

	}
	@Override
	public void addAlly(ICharacter character) {
		if(!mData.allAllies.contains(character))mData.allAllies.add(character);

	}

	@Override
	public void removeEnemy(int characterId) {
		if(mData.allEnemies.stream().anyMatch(chr->chr.getId()==characterId))mData.allEnemies.removeIf(enm->enm.getId() == characterId);

	}
	@Override
	public void removeAlly(int characterId) {
		if(mData.allAllies.stream().anyMatch(chr->chr.getId()==characterId))mData.allAllies.removeIf(enm->enm.getId() == characterId);

	}

	@Override
	public MsLocation getLocation() {
		return mCurrentCell.getLocation();
	}

	@Override
	public boolean move(MsLocation location) {
		if(!getArena().inArena(location))return false;
		setCell(getArena().cellAt(location));
		return true;
	}

	@Override
	public CharacterStatus gCharacterStatus() {
		if(mData.roundCount == 0) return mData.status = CharacterStatus.Free;

		if(mData.roundCount != 0&& mData.round<mData.roundCount-1){
			System.out.println("busy still "+(mData.roundCount-mData.round-1)+" left");

		} else if(mData.round == mData.roundCount-1){
			mData.round = 0;
			mData.roundCount = 0;
			mData.status = CharacterStatus.Free;
		}
		mData.round++;
		return mData.status;
	}

	@Override
	public void setCharacterStatus(CharacterStatus status, int roundCount) {
		mData.status = status;
		mData.roundCount = roundCount;
	}

}