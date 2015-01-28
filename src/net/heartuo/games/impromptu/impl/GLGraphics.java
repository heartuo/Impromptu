package net.heartuo.games.impromptu.impl;

import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;

public class GLGraphics {
	GLSurfaceView gameView;
	GL10 gl;
	
	GLGraphics(GLSurfaceView gameView) {
		this.gameView = gameView;
	}
	
	public GL10 getGL() {
		return gl;
	}
	
	void setGL(GL10 gl) {
		this.gl = gl;
	}
	
	public int getWidth() {
		return gameView.getWidth();
	}
	
	public int getHeight() {
		return gameView.getHeight();
	}
}
