package net.heartuo.games.impromptu.gl;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;

import net.heartuo.games.impromptu.impl.GLGraphics;

public class SpriteBatcher {        
    final float[] verticesBuffer;
    int bufferIndex;
    final Vertices vertices;
    int numSprites;    
    
    public SpriteBatcher(GLGraphics glGraphics, int maxSprites) {                
        this.verticesBuffer = new float[maxSprites*4*4];        
        this.vertices = new Vertices(glGraphics, false, true, maxSprites*4, maxSprites*6);
        this.bufferIndex = 0;
        this.numSprites = 0;
        
        short[] indices = new short[maxSprites*6];
        int len = indices.length;
        short j = 0;
        for (int i = 0; i < len; i += 6, j += 4) {
                indices[i + 0] = (short)(j + 0);
                indices[i + 1] = (short)(j + 1);
                indices[i + 2] = (short)(j + 2);
                indices[i + 3] = (short)(j + 2);
                indices[i + 4] = (short)(j + 3);
                indices[i + 5] = (short)(j + 0);
        }
        vertices.setIndices(indices, 0, indices.length);                
    }       
    
    public void beginBatch(Texture texture) {
        texture.bind();
        numSprites = 0;
        bufferIndex = 0;
    }
    
    public void endBatch() {
        vertices.setVertices(verticesBuffer, 0, bufferIndex);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
        vertices.unbind();
    }
    
    public void drawSprite(float x, float y, float width, float height, TexturePiece piece) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float x1 = x - halfWidth;
        float y1 = y - halfHeight;
        float x2 = x + halfWidth;
        float y2 = y + halfHeight;
        
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
        
        numSprites++;
    }
}