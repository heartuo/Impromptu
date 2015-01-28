package net.heartuo.games.impromptu.gl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import net.heartuo.games.impromptu.FileIO;
import net.heartuo.games.impromptu.impl.AndroidGame;
import net.heartuo.games.impromptu.impl.GLGraphics;

public class Texture {
	GLGraphics graphics;
	FileIO fileIO;
	String fileName;
	int textureId;
	int minFilter;
	int magFilter;
	public int width;
	public int height;
	
	public Texture(AndroidGame game, String fileName) {
		this.graphics = game.getGLGraphics();
		this.fileIO = game.getFileIO();
		this.fileName = fileName;
		load();
	}
	
	private void load() {
		GL10 gl = graphics.getGL();
		int[] textureIds = new int[1];
		gl.glGenTextures(1, textureIds, 0);
		textureId = textureIds[0];
		
		InputStream in = null;
		try {
			in = fileIO.readAsset(fileName);
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inScaled = false;
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST); //changeable
			gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			bitmap.recycle();
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load texture '" + fileName + "'", e);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					
				}
		}
	}
	
	public void reload() {
		load();
		bind();
		setFilters(minFilter, magFilter);
		graphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}
	
	public void setFilters(int minFilter, int magFilter) {
		this.minFilter = minFilter;
		this.magFilter = magFilter;
		GL10 gl = graphics.getGL();
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
	}
	
	public void bind() {
		GL10 gl = graphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
	}
	
	public void dispose() {
		GL10 gl = graphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);//????
		int[] textureIds = {textureId};
		gl.glDeleteTextures(1, textureIds, 0);
	}
}
