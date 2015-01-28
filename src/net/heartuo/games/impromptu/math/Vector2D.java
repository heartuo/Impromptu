package net.heartuo.games.impromptu.math;

import android.util.FloatMath;

public class Vector2D {
	public float x, y;
	
	public Vector2D() {
		
	}
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2D set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2D set(Vector2D other) {
		this.x = other.x;
		this.y = other.y;
		return this;
	}
	
	public Vector2D add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2D add(Vector2D other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2D addInverseY(float x, float y) {
		this.x += x;
		this.y -= y;
		return this;
	}
	
	public Vector2D addInverseY(Vector2D other) {
		this.x += other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2D addInverseX(float x, float y) {
		this.x -= x;
		this.y += y;
		return this;
	}
	
	public Vector2D addInverseX(Vector2D other) {
		this.x -= other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2D sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2D sub(Vector2D other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2D mul(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	public float norm() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public Vector2D normalize() {
		float norm = this.norm();
		if (norm != 0) {
			this.x /= norm;
			this.y /= norm;
		}
		return this;
	}
	
	public float distance(Vector2D other) {
		float tmpX = this.x - other.x;
		float tmpY = this.y - other.y;
		return (float) Math.sqrt(tmpX * tmpX + tmpY * tmpY);
	}
	
	public float distance(float x, float y) {
		float tmpX = this.x - x;
		float tmpY = this.y - y;
		return (float) Math.sqrt(tmpX * tmpX + tmpY * tmpY);
	}
}
