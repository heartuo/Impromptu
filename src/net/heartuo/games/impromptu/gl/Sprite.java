package net.heartuo.games.impromptu.gl;

import javax.microedition.khronos.opengles.GL10;

import net.heartuo.games.impromptu.impl.GLGraphics;

public class Sprite {
	TexturePiece piece;
	final float x;
	final float y;
	final float width;
	final float height;
	
	final Vertices vertices;
	
	public Sprite(GLGraphics graphics, TexturePiece piece, float x, float y, float width, float height) {
		this.piece = piece;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vertices = new Vertices(graphics, false, true, 4, 6);
		//set indices
		short[] indices = new short[6];
		for (short i = 0, j = 0; i < 6; i += 6, j += 4) {
			indices[i + 0] = (short)(j + 0);
			indices[i + 1] = (short)(j + 1);
			indices[i + 2] = (short)(j + 2);
			indices[i + 3] = (short)(j + 2);
			indices[i + 4] = (short)(j + 3);
			indices[i + 5] = (short)(j + 0);
			
			/*
			indices[i + 0] = (short)(j + 0);
			indices[i + 1] = (short)(j + 1);
			indices[i + 2] = (short)(j + 3);
			indices[i + 3] = (short)(j + 1);
			indices[i + 4] = (short)(j + 3);
			indices[i + 5] = (short)(j + 2);
			*/
		}
		vertices.setIndices(indices, 0, 6);
		////
		
		//set vertices
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		float x1 = x - halfWidth;
		float y1 = y - halfHeight;
		float x2 = x + halfWidth;
		float y2 = y + halfHeight;
		
		//note: texture coordinates is different from world coordinates! 
		
		int bufferIndex = 0;
		float[] verticesBuffer = new float[16];
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = piece.u1;
		verticesBuffer[bufferIndex++] = piece.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = piece.u2;
		verticesBuffer[bufferIndex++] = piece.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = piece.u2;
		verticesBuffer[bufferIndex++] = piece.v1;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = piece.u1;
		verticesBuffer[bufferIndex++] = piece.v1;
		
		vertices.setVertices(verticesBuffer, 0, bufferIndex);
		//
	}
	
	public void setTexturePiece(TexturePiece piece) {
		
		this.piece = piece;
		
		//set vertices
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		float x1 = x - halfWidth;
		float y1 = y - halfHeight;
		float x2 = x + halfWidth;	
		float y2 = y + halfHeight;
				
		//note: texture coordinates is different from world coordinates! 
				
		int bufferIndex = 0;
		float[] verticesBuffer = new float[16];
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = piece.u1;
		verticesBuffer[bufferIndex++] = piece.v2;
				
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = piece.u2;
		verticesBuffer[bufferIndex++] = piece.v2;
				
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = piece.u2;
		verticesBuffer[bufferIndex++] = piece.v1;
				
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = piece.u1;
		verticesBuffer[bufferIndex++] = piece.v1;
				
		vertices.setVertices(verticesBuffer, 0, bufferIndex);
		//
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void draw() {
		//bind and draw
		vertices.bind();
		vertices.draw(GL10.GL_TRIANGLES, 0, 6);
		vertices.unbind();
		//
	}
}
