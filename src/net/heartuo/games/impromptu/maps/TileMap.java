package net.heartuo.games.impromptu.maps;

import net.heartuo.games.impromptu.gl.Texture;
import java.util.List;
import java.util.ArrayList;

public class TileMap {
	public Texture map;
	public Tile[][] tiles;
	
	public static final short PLAIN = 0;
	public static final short ROUGH = 1;
	public static final short MOUNT = 2;
	public static final short AQUA = 3;
	public static final short ENTITY = 4;
	
	
	public TileMap(Texture map, short[][] terrains) {
		this.map = map;
		this.tiles = new Tile[terrains.length][terrains[0].length];
		
		for (int i = 0; i < terrains.length; i++) {
			for (int j = 0; j < terrains[0].length; j++) {
				tiles[i][j] = new Tile();
				tiles[i][j].terrain = terrains[i][j];
				tiles[i][j].x = i * Tile.sideLength + Tile.sideLength / 2;
				tiles[i][j].y = j * Tile.sideLength + Tile.sideLength / 2;
			}
		}
	}
	
	public Tile tileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public void setVisited(Tile tile) {
		tiles[(int)(tile.x - Tile.sideLength / 2) / (int)Tile.sideLength][(int)(tile.y - Tile.sideLength / 2) / (int)Tile.sideLength].visited = true;
	}
	
	public boolean visited(Tile tile) {
		return tiles[(int)(tile.x - Tile.sideLength / 2) / (int)Tile.sideLength][(int)(tile.y - Tile.sideLength / 2) / (int)Tile.sideLength].visited;
	}
	
	public boolean isValid(int i, int j) {
		if (i < tiles.length && i > -1 && j < tiles[0].length && j > -1)
			return true;
		else return false;
	}
	
	public List<Tile> getNeighbors(Tile tile) {
		List<Tile> neighbors = new ArrayList<Tile>();
		
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		
		for (int i = tileI - 1; i < tileI + 2; i++)
			for (int j = tileJ - 1; j < tileJ + 2; j++) {
				if (i != tileI || j != tileJ)
					if (isValid(i, j))
						neighbors.add(tiles[i][j]);
			}
		
		return neighbors;
		
	}
	
	public Tile getE(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI + 1][tileJ];
	}
	
	public Tile getW(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI - 1][tileJ];
	}
	
	public Tile getS(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI][tileJ - 1];
	}
	
	public Tile getN(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI][tileJ + 1];
	}
	
	public Tile getSE(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI + 1][tileJ - 1];
	}
	
	public Tile getSW(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI - 1][tileJ - 1];
	}
	
	public Tile getNE(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI + 1][tileJ + 1];
	}
	
	public Tile getNW(Tile tile) {
		int tileI = (int) ((tile.x - Tile.sideLength / 2) / Tile.sideLength);
		int tileJ = (int) ((tile.y - Tile.sideLength / 2) / Tile.sideLength);
		return tiles[tileI - 1][tileJ + 1];
	}
}
