package net.heartuo.games.impromptu;

public interface Game {
	public Input getInput();
	
	public FileIO getFileIO();
	
	public Audio getAudio();
	
	public Graphics getGraphics();
	
	public Screen getScreen();
	
	public Screen getStartScreen();
	
	public void setScreen(Screen screen);
}
