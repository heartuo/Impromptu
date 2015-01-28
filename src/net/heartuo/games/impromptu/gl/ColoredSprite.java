package net.heartuo.games.impromptu.gl;

import javax.microedition.khronos.opengles.GL10;

import net.heartuo.games.impromptu.impl.GLGraphics;

public class ColoredSprite {
	GLGraphics graphics;
	final float x;
	final float y;
	final float width;
	final float height;
	
	Vertices vertices;
	
	public ColoredSprite(GLGraphics graphics, float x, float y, float width, float height) {
		this.graphics = graphics;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vertices = new Vertices(graphics, true, false, 4, 6);
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
		float[] verticesBuffer = new float[24];
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = 1;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 1;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 1;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 1;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = 1;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 1;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 0;
		verticesBuffer[bufferIndex++] = 1;
		verticesBuffer[bufferIndex++] = 1;
		
		vertices.setVertices(verticesBuffer, 0, bufferIndex);
		//
	}
	
	public void draw() {
		//bind and draw
		vertices.bind();
		vertices.draw(GL10.GL_TRIANGLES, 0, 6);
		vertices.unbind();
		//
	}
}
