package net.heartuo.games.impromptu.maps;

import net.heartuo.games.impromptu.obj.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PathFinder {
	TileMap map;
	Unit unit;
	TileHeap open;
	Tile start, goal;
	
	public class Visited {
		
		public void add(Tile tile) {
			
		}
	}
	
	public PathFinder(TileMap map, Unit unit, Tile s, Tile g) {
		this.map = map;
		this.unit = unit;
		this.start = s;
		this.goal = g;
		this.open = new TileHeap(start, goal);
	}
	
	public List<Tile> reconstructPath(Map<Tile, Tile> dir, Tile goal) {
		List<Tile> path = new ArrayList<Tile>();
		Tile curr = goal;
		path.add(0, goal);
		
		while (curr != start) {
			path.add(0, dir.get(curr));
			curr = dir.get(curr);
		}
		
		return path;
	}
	
	public List<Tile> aStar() {
		map.setVisited(start);
		open.push(start);
		Tile curr;
		Map<Tile, Tile> dir = new HashMap<Tile, Tile>();
		
		while (!open.empty()) {
			curr = open.pop();
			if (curr == goal)
				return reconstructPath(dir, goal);
			
			List<Tile> neighbors = map.getNeighbors(curr);
			for (Tile n : neighbors) {
				if (!map.visited(n)) {
					map.setVisited(n);
					if (unit.isWalkable(n)) {
						open.push(n);
						dir.put(n, curr);
					}
				}
			}
		}
		
		return reconstructPath(dir, goal);
		
	}
}
