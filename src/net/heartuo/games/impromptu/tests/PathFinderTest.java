package net.heartuo.games.impromptu.tests;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import net.heartuo.games.impromptu.Game;
import net.heartuo.games.impromptu.Screen;
import net.heartuo.games.impromptu.gl.*;
import net.heartuo.games.impromptu.math.*;
import net.heartuo.games.impromptu.Input.TouchEvent;
import net.heartuo.games.impromptu.gl.Camera2D;
import net.heartuo.games.impromptu.gl.Texture;
import net.heartuo.games.impromptu.gui.Button;
import net.heartuo.games.impromptu.impl.AndroidGame;
import net.heartuo.games.impromptu.impl.GLGraphics;
import net.heartuo.games.impromptu.obj.entities.*;
import net.heartuo.games.impromptu.maps.*;

public class PathFinderTest extends Screen {
	GLGraphics graphics;
	Camera2D camera;
	TileMap map;
	PathFinder finder;
	Tile start;
	Tile goal;
	
	boolean startDragging = false;
    boolean goalDragging = false;
	
	Footman footman = new Footman();
	
	Font courier;
	
	Texture buttonTex;
	Texture colors;
	Texture courierTex;
	TexturePiece blank, black, grey, red, green, blue;
	Button search, clearPath, clearAll;
	Sprite bg;
	
	public PathFinderTest(Game game) {
		super(game);
		graphics = ((AndroidGame)game).getGLGraphics();
		
		camera = new Camera2D(graphics, 1024, 640);
		
		buttonTex = new Texture((AndroidGame)game, "images/button.png");
		colors = new Texture((AndroidGame)game, "images/colors.png");
		courierTex = new Texture((AndroidGame)game, "images/courier_white.png");
		
		courier = new Font(courierTex, 0, 0, 32, 32, 8);
		
		blank = new TexturePiece(colors, 4, 0, 1, 1);
		black = new TexturePiece(colors, 5, 0, 1, 1);
		grey = new TexturePiece(colors, 6, 0, 1, 1);
		red = new TexturePiece(colors, 0, 0, 1, 1);
		green = new TexturePiece(colors, 1, 0, 1, 1);
		blue = new TexturePiece(colors, 2, 0, 1, 1);
		
		map = new TileMap(colors, new short[32][17]);
		
		start = map.tiles[0][0];
		//map.tileAt(0, 0).setTerrain((short)4);
		goal = map.tiles[31][16];
		
		bg = new Sprite(graphics, blank, 512, 368, 1024, 544);
		
		finder = new PathFinder(map, footman, start, goal);
		
		search = new Button(graphics, buttonTex, 124, 48, 248, 96);
		clearPath = new Button(graphics, buttonTex, 372, 48, 248, 96);
		clearAll = new Button(graphics, buttonTex, 620, 48, 248, 96);
		
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
		search.draw();
		clearPath.draw();
		clearAll.draw();
		//buttonTex.dispose();
		
		courierTex.bind();
		
		courier.drawText(graphics, "SEARCH", 0+32, 48, 16, 16);
		courier.drawText(graphics, "CLEAR PATH", 248+32, 48, 16, 16);
		courier.drawText(graphics, "CLEAR ALL", 496+32, 48, 16, 16);
		
		
		colors.bind();
		bg.draw();
		
		for (int i = 0; i < map.tiles.length; i++)
			for (int j = 0; j < map.tiles[0].length; j++) {
				int x = i * 32 + 16;
				int y = (16 - j) * 32 + 16 + 96;
				
				if (map.tileAt(i, j) == start) {
					Sprite currTile = new Sprite(graphics, red, x, y, 32, 32);
					currTile.draw();
					
				}
				else if (map.tileAt(i, j) == goal) {
					Sprite currTile = new Sprite(graphics, blue, x, y, 32, 32);
					currTile.draw();
				}
				else if (map.tileAt(i, j).terrain == 2) {
					Sprite currTile = new Sprite(graphics, grey, x, y, 32, 32);
					currTile.draw();
				}
				else if (map.tileAt(i, j).terrain == 1) {
					Sprite currTile = new Sprite(graphics, green, x, y, 32, 32);
					currTile.draw();
				}
				else {
					Sprite currTile = new Sprite(graphics, blank, x, y, 32, 32);
					currTile.draw();
				}
			}
			
		//colors.dispose();
		
		
		//1024 * 544 in opengl units
		//let it be 64 * 34 tiles with each tile's length being 16 units
		
		//the other 1024 * 96 space is for buttons
		
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
       
        
        if (len == 1) {
        	TouchEvent event = touchEvents.get(0);
        	
        	Vector2D point = camera.touchToWorld(event.x, event.y);
    		int tileX = (int)point.x / 32;
    		int tileY = 16 - (int)(point.y - 96) / 32;
        	
        	if (event.type == TouchEvent.TOUCH_DOWN) {
        		
        		
        		if (search.inButton(point))
        			search.down();
        		else
        			search.up();
        		if (clearPath.inButton(point))
        			clearPath.down();
        		else
        			clearPath.up();
        		if (clearAll.inButton(point))
        			clearAll.down();
        		else
        			clearAll.up();
        			
        		
        		if (tileX < map.tiles.length && tileY < map.tiles[0].length) {
        			if (map.tileAt(tileX, tileY) == start) {
        				startDragging = true;
        			}
        			else if (map.tileAt(tileX, tileY) == goal) {
        				goalDragging = true;
        			}
        			else if (map.tileAt(tileX, tileY).terrain == 0)
        				map.tileAt(tileX, tileY).setTerrain((short)2);
        			else if (map.tileAt(tileX, tileY).terrain == 2)
        				map.tileAt(tileX, tileY).setTerrain((short)0);
        		}
        	
        		//finder = new PathFinder(map, footman, start, goal);
        	}
        	
        	else if (event.type == TouchEvent.TOUCH_DRAGGED) {
        		
        		if (search.inButton(point)) {
        			search.down();
        			clearPath.up();
        			clearAll.up();
        		}
        		else if (clearPath.inButton(point)) {
        			search.up();
        			clearPath.down();
        			clearAll.up();
        		}
        		else if (clearAll.inButton(point)) {
        			search.up();
        			clearPath.up();
        			clearAll.down();
        		}
        		else {
        			search.up();
        			clearPath.up();
        			clearAll.up();
        		}
        		
        		if (startDragging) {
        			if(map.tileAt(tileX, tileY) != goal)
        				start = map.tileAt(tileX, tileY);
        			
        		}
        		
        		else if (goalDragging) {
        			if(map.tileAt(tileX, tileY) != start)
        				goal = map.tileAt(tileX, tileY);
        		}
        		
        		else {
        			if (map.tileAt(tileX, tileY).terrain == 0)
        				map.tileAt(tileX, tileY).setTerrain((short)2);
        			else if (map.tileAt(tileX, tileY).terrain == 2)
        				map.tileAt(tileX, tileY).setTerrain((short)0);
        		}
        	}
        	
        	if (event.type == TouchEvent.TOUCH_UP) {
        		if (startDragging) {
        			startDragging = false;
        		}
        		else if (goalDragging) {
        			goalDragging = false;
        		}
        		
        		if (search.inButton(point)) {
        			List<Tile> path = finder.aStar();
        			for (Tile n : path) {
        				n.setTerrain((short)1);
        			}
        		}
        		else if (clearAll.inButton(point)) {
        			for (int i = 0; i < map.tiles.length; i++)
        				for (int j = 0; j < map.tiles[0].length; j++) {
        					map.tileAt(i, j).terrain = 0;
        					map.tileAt(i, j).visited = false;
        				}
        			finder = new PathFinder(map, footman, start, goal);
        		}
        		else if (clearPath.inButton(point)) {
        			for (int i = 0; i < map.tiles.length; i++)
        				for (int j = 0; j < map.tiles[0].length; j++) {
        					if (map.tileAt(i, j).terrain == 1)
        						map.tileAt(i, j).terrain = 0;
        					map.tileAt(i, j).visited = false;
        				}
        			finder = new PathFinder(map, footman, start, goal);
        		}
        		
        		search.up();
        		clearPath.up();
        		clearAll.up();
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
