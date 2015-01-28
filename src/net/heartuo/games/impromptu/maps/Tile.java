package net.heartuo.games.impromptu.maps;

public class Tile {
	public float x, y;
	public Tile parent;
	public short terrain;
	public static final float sideLength = 100;
	public boolean visited; //used in pathfinding
	
	public Tile() {
		this.parent = null;
		this.visited = false;
	}
	
	public void setTerrain(short t) {
		this.terrain = t;
	}
	
	public float getHeuristic(Tile start, Tile goal) {
		float dxG = Math.abs(x - start.x);
		float dyG = Math.abs(y - start.y);
		float g = Math.max(dxG, dyG);
		
		float dxH = Math.abs(x - goal.x);
		float dyH = Math.abs(y - goal.y);
		float h = Math.max(dxH, dyH);
		
		return g + h;
	}
	
}
