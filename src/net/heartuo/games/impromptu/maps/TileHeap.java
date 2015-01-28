package net.heartuo.games.impromptu.maps;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class TileHeap {
	public List<Tile> elems;
	private Tile start;
	private Tile goal;
	
	public TileHeap(Tile start, Tile goal) {
		this.start = start;
		this.goal = goal;
		this.elems = new ArrayList<Tile>();
	}
	
	public TileHeap(Tile start, Tile goal, List<Tile> elems) {
		this.start = start;
		this.goal = goal;
		this.elems = elems;
	}
	
	public int root() {
		return 0;
	}
	
	public int leftChild(int currIdx) {
		return 2 * (currIdx + 1) - 1;
	}
	
	public int rightChild(int currIdx) {
		return 2 * (currIdx + 1);
	}
	
	public int parent(int currIdx) {
		return (currIdx - 1) / 2;
	}
	
	public boolean hasAChild(int currIdx) {
		return (leftChild(currIdx) < elems.size());
	}
	
	public boolean higherPrio(Tile a, Tile b) {
		return (a.getHeuristic(start, goal) < b.getHeuristic(start, goal));
	}
	
	public int maxPrioChild(int currIdx) {
		if (leftChild(currIdx) > elems.size() - 1)
			return rightChild(currIdx);
		if (rightChild(currIdx) > elems.size() - 1)
			return leftChild(currIdx);
		if (higherPrio(elems.get(leftChild(currIdx)), elems.get(rightChild(currIdx))))
			return leftChild(currIdx);
		else
			return rightChild(currIdx);
	}
	
	public void heapifyDown(int currIdx) {
		if (!hasAChild(currIdx))
			return;
		int childIdx = maxPrioChild(currIdx);
		if (higherPrio(elems.get(childIdx), elems.get(currIdx))) {
			Collections.swap(elems, childIdx, currIdx);
			heapifyDown(childIdx);
		}
	}
	
	public void heapifyUp(int currIdx) {
		if (currIdx == root())
			return;
		int parentIdx = parent(currIdx);
		if (higherPrio(elems.get(currIdx), elems.get(parentIdx))) {
			Collections.swap(elems, currIdx, parentIdx);
			heapifyUp(parentIdx);
		}
	}
	
	public Tile pop() {
		Tile front = elems.get(0);
		elems.set(0, elems.get(elems.size() - 1));
		elems.remove(elems.size() - 1);
		heapifyDown(0);
		return front;
	}
	
	public Tile peek() {
		return elems.get(0);
	}
	
	public void push(Tile elem) {
		elems.add(elem);
		heapifyUp(elems.size() - 1);
	}
	
	boolean empty() {
		return elems.isEmpty();
	}
	
}
