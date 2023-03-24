package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
	
	@Override
	public void start(GameData gameData, World world) {
		//asteroidSplitTest(gameData, world);
	}
	
	private void asteroidSplitTest(GameData gameData, World world){
		float deceleration = 0;
		float acceleration = 300000f;
		float maxSpeed = 30;
		float rotationSpeed = 3;
		//Big asteroid
		{
			Asteroid asteroid = new Asteroid(8);
			asteroid.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
			asteroid.add(new PositionPart(gameData.getDisplayWidth() / 4, gameData.getDisplayHeight() / 2, 0));
			
			MovingPart movingPart = asteroid.getPart(MovingPart.class);
			movingPart.setUp(true);
			
			asteroid.setShapeX(new float[6]);
			asteroid.setShapeY(new float[6]);
			asteroid.add(new LifePart((asteroid.getSize() - 1) / 2 + 1));
			
			world.addEntity(asteroid);
		}
		//Small asteroid
		{
			Entity asteroid = new Entity();
			asteroid.add(new PositionPart(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2, 0));
			asteroid.add(new MovingPart(0, 0, 0, 0));
			
			asteroid.setShapeX(new float[6]);
			asteroid.setShapeY(new float[6]);
			asteroid.add(new LifePart(2));
			
			world.addEntity(asteroid);
		}
	}
	
	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		for (Entity asteroid : world.getEntities(Asteroid.class)) {
			world.removeEntity(asteroid);
		}
	}
}
