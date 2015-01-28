package net.heartuo.games.impromptu;

import java.util.List;
import java.util.ArrayList;

public class Pool<T> {
	public List<T> freeObjects;
	public int maxSize;
	public PoolObjectCreator<T> creator;
	
	public interface PoolObjectCreator<T> {
		public T createObject();
	}
	
	public Pool(PoolObjectCreator<T> creator, int maxSize) {
		this.creator = creator;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	}
	
	public T useObject() {
		if (freeObjects.size() != 0) 
			return freeObjects.remove(freeObjects.size() - 1);
		else
			return creator.createObject();
	}
	
	public void freeObject(T object) {
		if (freeObjects.size() < maxSize) 
			freeObjects.add(object);
	}
}
