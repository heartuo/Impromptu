package net.heartuo.games.impromptu.gl;
import java.lang.String;

import net.heartuo.games.impromptu.impl.GLGraphics;

public class Font {
	public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TexturePiece[] glyphs = new TexturePiece[96];
    
    //for the font "courier", the parameters are "texture, 0, 0, 32, 32, 8"
    
    public Font(Texture texture, int offsetX, int offsetY, int glyphWidth, int glyphHeight, int glyphsPerRow) {  	
    	this.texture = texture;
    	this.glyphWidth = glyphWidth;
    	this.glyphHeight = glyphHeight;
    	
    	int x = offsetX;
    	int y = offsetY;
    	
    	for (int i = 0; i < 96; i++) {
    		glyphs[i] = new TexturePiece(texture, x, y, glyphWidth, glyphHeight);
    		
    		x += glyphWidth;
    		
    		if (x == glyphWidth * glyphsPerRow + offsetX) {
    			x = offsetX;
    			y += glyphHeight;
    		}
    	}
    }
    
    public void drawText(GLGraphics graphics, String text, float x, float y, float width, float height) {
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if (c < 0 || c > glyphs.length - 1) 
                continue;
            
            TexturePiece glyph = glyphs[c];
            Sprite curr = new Sprite(graphics, glyph, x, y, width, height);
            curr.draw();
            
            x += width;
        }
    }
}