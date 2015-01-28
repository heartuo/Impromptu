package net.heartuo.games.impromptu.impl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import net.heartuo.games.impromptu.*;

public abstract class AndroidGame extends Activity implements Game, Renderer {
	enum GameState {
		init, 
		running, 
		paused, 
		finished, 
		idle
	}
	
	GLSurfaceView gameView;
	GLGraphics graphics;
	AndroidAudio audio;
	AndroidInput input;
	AndroidFileIO fileIO;
	Screen screen;
	GameState state = GameState.init;
	Object stateChanged = new Object();
	long startTime = System.nanoTime();
	WakeLock wakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		gameView = new GLSurfaceView(this);
		gameView.setRenderer(this);
		setContentView(gameView);
		
		graphics = new GLGraphics(gameView);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, gameView, 1, 1);
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		//wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "AndroidGame");
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "AndroidGame");
	}
	
	public void onResume() {
		super.onResume();
		gameView.onResume();
		wakeLock.acquire();
		 
		//View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		//decorView.setSystemUiVisibility(uiOptions);
		gameView.setSystemUiVisibility(uiOptions);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		graphics.setGL(gl);
		
		synchronized(stateChanged) {
			if (state == GameState.init)
				screen = getStartScreen();
			state = GameState.running;
			screen.resume();
			startTime = System.nanoTime();
		}
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		GameState state = null;
		
		synchronized(stateChanged) {
			state = this.state;
		}
		
		if (state == GameState.running) {
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();
			
			screen.update(deltaTime);
			screen.present(deltaTime);
		}
		
		if (state == GameState.paused) {
			screen.pause();
			synchronized(stateChanged) {
				this.state = GameState.idle;
				stateChanged.notifyAll();
			}
		}
		
		if (state == GameState.finished) {
			screen.pause();
			screen.dispose();
			synchronized(stateChanged) {
				this.state = GameState.idle;
				stateChanged.notifyAll();
			}
		}
		
	}
	
	@Override
	public void onPause() {
		synchronized(stateChanged) {
			if (isFinishing()) 
				state = GameState.finished;
			else
				state = GameState.paused;
			while (true) {
				try {
					stateChanged.wait();
					break;
				} catch(InterruptedException e) {
					
				}
			}
		}
		wakeLock.release();
		gameView.onPause();
		super.onPause();
	}
	
	@Override
	public Graphics getGraphics() {
		throw new IllegalStateException("Using OpenGL.");
	}
	
	public GLGraphics getGLGraphics() {
		return graphics;
	}
	
	@Override
	public Input getInput() {
		return input;
	}
	
	@Override
	public FileIO getFileIO() {
		return fileIO;
	}
	
	@Override
	public Audio getAudio() {
		return audio;
	}
	
	@Override
	public void setScreen(Screen screen) {
		if (screen == null) 
			throw new IllegalArgumentException("Screen must not be null. ");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	@Override
	public Screen getScreen() {
		return screen;
	}
}
