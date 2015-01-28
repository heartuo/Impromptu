package net.heartuo.games.impromptu.gui;

import net.heartuo.games.impromptu.gl.Sprite;
import net.heartuo.games.impromptu.gl.Texture;
import net.heartuo.games.impromptu.gl.TexturePiece;
import net.heartuo.games.impromptu.impl.GLGraphics;
import net.heartuo.games.impromptu.math.Vector2D;

public class Button {
	TexturePiece up;
	TexturePiece down;
	Sprite button;
	float x1, x2, y1, y2;
	
	public Button(GLGraphics graphics, Texture buttonTex, float x, float y, float width, float height) {
		
		this.up = new TexturePiece(buttonTex, (int) 1, (int) 1, 62, 24);
		this.down = new TexturePiece(buttonTex, (int) 1, (int) 39, 62, 24);
		
		this.button = new Sprite(graphics, up, x, y, width, height);
		
		float halfWidth = button.getWidth() / 2;
		float halfHeight = button.getHeight() / 2;
		x1 = button.getX() - halfWidth;
		y1 = button.getY() - halfHeight;
		x2 = button.getX() + halfWidth;
		y2 = button.getY() + halfHeight;
	}
	
	public boolean inButton(float x, float y) {
		if (x > x1 && x < x2 && y > y1 && y < y2)
			return true;
		else
			return false;
	}
	
	public boolean inButton(Vector2D point) {
		if (point.x > x1 && point.x < x2 && point.y > y1 && point.y < y2)
			return true;
		else
			return false;
	}
	
	public void up() {
		this.button.setTexturePiece(up);
	}
	
	public void down() {
		this.button.setTexturePiece(down);
	}
	
	public void draw() {
		this.button.draw();
	}
}
