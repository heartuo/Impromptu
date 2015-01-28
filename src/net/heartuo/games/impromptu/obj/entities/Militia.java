package net.heartuo.games.impromptu.obj.entities;

import net.heartuo.games.impromptu.obj.Unit;
import net.heartuo.games.impromptu.maps.Tile;

public class Militia extends Unit {
	public Militia() {
		super(100, 200, 100, 100);
		this.maxHealth = 250;
		this.currentHealth = maxHealth;
		this.armor = 1;
		this.magicResist = 0;
		this.magicImmunity = false;
		this.moveSpeed = 200;
		this.attackSpeed = 1;
		this.upperDamage = 12;
		this.lowerDamage = 8;
		this.status = 1;
	}
	
	@Override
	public boolean isWalkable(Tile tile) {
		if (tile.terrain == 0) return true;
		else return false;
	}
}
