package net.heartuo.games.impromptu.obj;

import net.heartuo.games.impromptu.obj.Entity;
import net.heartuo.games.impromptu.maps.Tile;

public abstract class Unit extends Entity {
	protected int maxHealth;
	protected int currentHealth;
	protected int armor;
	protected int magicResist;
	protected boolean magicImmunity;
	protected int moveSpeed;
	protected int attackSpeed;
	protected int upperDamage;
	protected int lowerDamage;
	protected int status;
	
	public Unit(float width, float height, float baseWidth, float baseHeight) {
		super(width, height, baseWidth, baseHeight);
	}
	
	public Unit() {
		super(0, 0, 0, 0);
	}
	
	public boolean isDead() {
		return (currentHealth == 0);
	}
	
	public abstract boolean isWalkable(Tile tile);
}
