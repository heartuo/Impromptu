package net.heartuo.games.impromptu.maps;

public class TileLinkedList {
	public Tile tile;
	public TileLinkedList next;
	
	public TileLinkedList(Tile tile, TileLinkedList next) {
		this.tile = tile;
		this.next = next;
	}
	
	public boolean confirm(Tile tile) {
		if (this.tile == tile) return true;
		else {
			if (this.next == null) {
				return false;
			}
			else {
				return this.next.confirm(tile);
			}
		}
	}
	
	public TileLinkedList remove(Tile tile, TileLinkedList head) {
		if (this.tile == tile) {
			if (this.tile == head.tile) {
				head = this.next;
			}
			return this;
		}
		else {
			if (this.next == null) {
				return null;
			}
			else {
				if (this.next.tile == tile) this.next = this.next.next;
				return this.next.remove(tile, head);
			}
		}
	}
	
	public TileLinkedList insert(Tile tile) {
		if (this.next == null) {
			this.next = new TileLinkedList(tile, null);
			return this;
		}
		else {
			return this.next.insert(tile);
		}
	}
	
	public TileLinkedList insertSorted(Tile tile, Tile start, Tile goal, TileLinkedList head) {
		TileLinkedList tmp = new TileLinkedList(tile, null);
		//if (head.tile == null) head.insert(tile);
		if (tile.getHeuristic(start, goal) > this.tile.getHeuristic(start, goal)) {
			if (this.next == null) {
				this.next = tmp;
				return this;
			}
			else {
				this.next = this.next.insertSorted(tile, start, goal, head);
				return this;
			}
		}
		else {
			tmp.next = this;
			if (this == head) head = tmp;
			return tmp;
		}
	}
}
