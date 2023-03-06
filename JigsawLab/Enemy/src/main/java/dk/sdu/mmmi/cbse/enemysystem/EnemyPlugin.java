package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
	
	private Entity enemy;
	
	public EnemyPlugin() {
	}
	
	@Override
	public void start(GameData gameData, World world) {
		// Add entities to the world
		enemy = createEnemy(gameData);
		world.addEntity(enemy);
	}
	
	private Entity createEnemy(GameData gameData) {
		
		float deceleration = 10;
		float acceleration = 200;
		float maxSpeed = 300;
		float rotationSpeed = 5;
		float x = gameData.getDisplayWidth() / 4;
		float y = gameData.getDisplayHeight() / 4;
		float radians = 3.1415f / 2;
		
		Entity enemyShip = new Enemy();
		enemyShip.setRadius(8);
		enemyShip.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
		enemyShip.add(new PositionPart(x, y, radians));
		enemyShip.add(new LifePart(1));
		
		return enemyShip;
	}
	
	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		world.removeEntity(enemy);
	}
	
}
