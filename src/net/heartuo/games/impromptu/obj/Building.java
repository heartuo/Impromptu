package net.heartuo.games.impromptu.obj;

import net.heartuo.games.impromptu.obj.Entity;

public class Building extends Entity {
	protected int maxHealth;
	protected int currentHealth;
	protected int armor;
	protected int magicResist = 0;
	protected boolean magicImmunity = true;
	protected int status;
	
	public Building(float width, float height, float baseWidth, float baseHeight) {
		super(width, height, baseWidth, baseHeight);
	}
	
	public Building() {
		super(0, 0, 0, 0);
	}
	
	public boolean isRuined() {
		return (currentHealth == 0);
	}
}
