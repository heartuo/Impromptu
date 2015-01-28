package net.heartuo.games.impromptu.gl;

public class TexturePiece {
	public final Texture texture;
	public float u1;
	public float v1;
	public float u2;
	public float v2;
	
	public TexturePiece(Texture texture, int x, int y, int width, int height) {
		this.u1 = (float) x / (float) texture.width;
		this.v1 = (float) y / (float) texture.height;
		this.u2 = (float) (x + width) / (float) texture.width;
		this.v2 = (float) (y + height) / (float) texture.height;
		
		this.texture = texture;
	}
}
