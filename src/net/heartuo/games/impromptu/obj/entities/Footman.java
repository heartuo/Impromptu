package net.heartuo.games.impromptu.obj.entities;

import net.heartuo.games.impromptu.obj.Unit;
import net.heartuo.games.impromptu.maps.Tile;

public class Footman extends Unit{
	public Footman() {
		super(100, 200, 100, 100);
		this.maxHealth = 300;
		this.currentHealth = maxHealth;
		this.armor = 2;
		this.magicResist = 0;
		this.magicImmunity = false;
		this.moveSpeed = 200;
		this.attackSpeed = 1;
		//attack threshold: 60
		this.upperDamage = 20;
		this.lowerDamage = 10;
		this.status = 1;
	}
	
	@Override
	public boolean isWalkable(Tile tile) {
		if (tile.terrain == 0) return true;
		else return false;
	}
}