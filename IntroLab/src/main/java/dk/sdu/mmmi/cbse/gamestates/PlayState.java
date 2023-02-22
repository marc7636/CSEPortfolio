package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.Random;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	private ShapeRenderer srEnemy;
	
	private Player player;
	private Enemy enemy;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		srEnemy = new ShapeRenderer();
		
		player = new Player();
		enemy = new Enemy();
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		enemy.update(dt);
		
	}
	
	public void draw() {
		player.draw(sr);
		enemy.draw(srEnemy);
	}
	
	Random rng = new Random();
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		
		double rndm = rng.nextDouble();
		if (rndm < 0.25d){
			enemy.setLeft(true);
			enemy.setRight(false);
			enemy.setUp(false);
		} else if (rndm < 0.5d) {
			enemy.setLeft(false);
			enemy.setRight(true);
			enemy.setUp(false);
		} else if (rndm < 0.75d) {
			enemy.setLeft(false);
			enemy.setRight(false);
			enemy.setUp(true);
		} else {
			enemy.setLeft(false);
			enemy.setRight(false);
			enemy.setUp(false);
		}
	}
	
	public void dispose() {}
	
}









