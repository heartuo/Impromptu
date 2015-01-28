package net.heartuo.games.impromptu;

public abstract class Screen {
	protected final Game game;
	
	public Screen(Game game) {
		this.game = game;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime);
	
	public abstract void resume();
	
	public abstract void pause();
	
	public abstract void dispose();
}
