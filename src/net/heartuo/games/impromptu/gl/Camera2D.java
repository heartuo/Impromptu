package net.heartuo.games.impromptu.gl;

import javax.microedition.khronos.opengles.GL10;

import net.heartuo.games.impromptu.math.Vector2D;
import net.heartuo.games.impromptu.impl.GLGraphics;

public class Camera2D {
	public Vector2D position;
	public float zoom;
	public final float frustumWidth;
	public final float frustumHeight;
	final GLGraphics graphics;
	
	public Camera2D(GLGraphics graphics, float frustumWidth, float frustumHeight) {
		this.graphics = graphics;
		this.frustumWidth = frustumWidth;
		this.frustumHeight = frustumHeight;
		this.position = new Vector2D(frustumWidth / 2, frustumHeight / 2);
		this.zoom = 1.0f;
	}
	
	public void setup() {
		GL10 gl = graphics.getGL();
		gl.glViewport(0, 0, graphics.getWidth(), graphics.getHeight());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(position.x - frustumWidth / 2 * zoom, position.x + frustumWidth / 2 * zoom, position.y - frustumHeight / 2 * zoom, position.y + frustumHeight / 2 * zoom, 1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public Vector2D touchToWorld(Vector2D touch) {
		Vector2D world = new Vector2D();
		world.x = (touch.x / (float) graphics.getWidth()) * frustumWidth * zoom;
		world.y = (1 - touch.y / (float) graphics.getHeight()) * frustumHeight * zoom;
		world.add(position).sub(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
		
		return world;
	}
	
	public Vector2D touchToWorld(float x, float y) {
		Vector2D world = new Vector2D();
		world.x = (x / (float) graphics.getWidth()) * frustumWidth * zoom;
		world.y = (1 - y / (float) graphics.getHeight()) * frustumHeight * zoom;
		world.add(position).sub(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
		
		return world;
	}
}
