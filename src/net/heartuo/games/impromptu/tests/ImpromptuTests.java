package net.heartuo.games.impromptu.tests;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.heartuo.games.impromptu.Game;
import net.heartuo.games.impromptu.Input.TouchEvent;
import net.heartuo.games.impromptu.gui.*;
import net.heartuo.games.impromptu.Screen;
import net.heartuo.games.impromptu.gl.Camera2D;
import net.heartuo.games.impromptu.gl.Sprite;
import net.heartuo.games.impromptu.gl.Texture;
import net.heartuo.games.impromptu.gl.TexturePiece;
import net.heartuo.games.impromptu.impl.AndroidGame;
import net.heartuo.games.impromptu.impl.GLGraphics;
import net.heartuo.games.impromptu.math.Vector2D;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImpromptuTests extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		return new StartMenu(this);
	}

	public class StartMenu extends Screen {
		GLGraphics graphics;
		Camera2D camera;
		
		Texture buttonTex;
		Button button;
		
		public StartMenu(Game game) {
			super(game);
			graphics = ((AndroidGame)game).getGLGraphics();
			
			camera = new Camera2D(graphics, 1024, 640);
			
			buttonTex = new Texture((AndroidGame)game, "images/button.png");
			button = new Button(graphics, buttonTex, 900, 592, 248, 96);
			
		}
		
		@Override
		public void present(float deltaTime) {
			GL10 gl = graphics.getGL();
			camera.setup();
			
			gl.glClearColor(0, 0, 0, 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			
			gl.glEnable(GL10.GL_TEXTURE_2D);
			
			buttonTex.bind();
			button.draw();
			
		}
		
		@Override
		public void update(float deltaTime) {
			List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	        game.getInput().getKeyEvents();
	        
	        int len = touchEvents.size();
	        
	        if (len == 1) {
	        	TouchEvent event = touchEvents.get(0);
	        	
	        	if (event.type == TouchEvent.TOUCH_DOWN) {
	        		if (button.inButton(camera.touchToWorld(event.x, event.y)))
	        			button.down();
	        	}
	        	
	        	if (event.type == TouchEvent.TOUCH_DRAGGED) {
	        		if (button.inButton(camera.touchToWorld(event.x, event.y)))
	        			button.down();
	        		else
	        			button.up();
	        	}
	        	
	        	if (event.type == TouchEvent.TOUCH_UP) {
	        		if (button.inButton(camera.touchToWorld(event.x, event.y)))
	        			game.setScreen(new PathFinderTest(game));
	        		else
	        			button.up();
	        	}
	        }
		}
		
		@Override
		public void pause() {
			
		}
		
		@Override
		public void resume() {
			
		}
		
		@Override
		public void dispose() {
			
		}
		
	}
	
	@Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {         
        super.onSurfaceCreated(gl, config);
    }     
    
    @Override
    public void onPause() {
        super.onPause();
    }
    
}
