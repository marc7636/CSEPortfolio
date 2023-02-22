package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;

public class AsteroidController implements IEntityProcessingService {
	Random rng = new Random();
	
	@Override
	public void process(GameData gameData, World world) {
		if (rng.nextDouble() < (0.20f * gameData.getDelta())){
			world.addEntity(createEnemy(gameData));
		}
		
		for (Entity enemy : world.getEntities(Asteroid.class)) {
			PositionPart positionPart = enemy.getPart(PositionPart.class);
			MovingPart movingPart = enemy.getPart(MovingPart.class);
			
			updateEntity(enemy, movingPart);
			
			movingPart.process(gameData, enemy);
			positionPart.process(gameData, enemy);
			
			updateShape((Asteroid)enemy);
		}
	}
	
	private Entity createEnemy(GameData gameData) {
		float deceleration = 0;
		float acceleration = 300000f;
		float maxSpeed = 100;
		float rotationSpeed = 3;
		PositionPart coords = getRandomSpawn(gameData);
		
		OptionalInt randomSize = rng.ints(1, 3, 9).findAny();
		Entity asteroid = new Asteroid(randomSize.isPresent() ? randomSize.getAsInt() : 8);
		asteroid.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
		asteroid.add(coords);
		
		MovingPart movingPart = asteroid.getPart(MovingPart.class);
		movingPart.setUp(true);
		
		asteroid.setShapeX(new float[6]);
		asteroid.setShapeY(new float[6]);
		
		return asteroid;
	}
	
	private PositionPart getRandomSpawn(GameData gameData){
		float x;
		float y;
		
		OptionalDouble randomX = rng.doubles(1, 0, gameData.getDisplayWidth()).findAny();
		OptionalDouble randomY = rng.doubles(1, 0, gameData.getDisplayHeight()).findAny();
		double randomNumber = rng.nextDouble();
		if (randomNumber < 0.25){
			x = 0;
			y = randomY.isPresent() ? (float)randomY.getAsDouble() : 0;
		} else if (randomNumber < 0.5){
			x = randomX.isPresent() ? (float)randomX.getAsDouble() : 0;
			y = 0;
		} else if (randomNumber < 0.75){
			x = gameData.getDisplayWidth();
			y = randomY.isPresent() ? (float)randomY.getAsDouble() : 0;
		} else {
			x = randomX.isPresent() ? (float)randomX.getAsDouble() : 0;
			y = gameData.getDisplayHeight();
		}
		
		OptionalDouble randomRadians = rng.doubles(1, 0, 2 * Math.PI).findAny();
		float radians = randomRadians.isPresent() ? (float)randomRadians.getAsDouble() : ((float)Math.PI / 2);
		
		return new PositionPart(x,y,radians);
	}
	
	private void updateShape(Asteroid asteroid) {
		float[] shapex = asteroid.getShapeX();
		float[] shapey = asteroid.getShapeY();
		PositionPart positionPart = asteroid.getPart(PositionPart.class);
		float x = positionPart.getX();
		float y = positionPart.getY();
		float radians = positionPart.getRadians();
		
		shapex[0] = (float) (x + Math.cos(radians) * asteroid.getScale());
		shapey[0] = (float) (y + Math.sin(radians) * asteroid.getScale());
		
		shapex[1] = (float) (x + Math.cos(radians - 2 * Math.PI / 6) * asteroid.getScale());
		shapey[1] = (float) (y + Math.sin(radians - 2 * Math.PI / 6) * asteroid.getScale());
		
		shapex[2] = (float) (x + Math.cos(radians - 4 * Math.PI / 6) * asteroid.getScale());
		shapey[2] = (float) (y + Math.sin(radians - 4 * Math.PI / 6) * asteroid.getScale());
		
		shapex[3] = (float) (x + Math.cos(radians + Math.PI) * asteroid.getScale());
		shapey[3] = (float) (y + Math.sin(radians + Math.PI) * asteroid.getScale());
		
		shapex[4] = (float) (x + Math.cos(radians + 4 * Math.PI / 6) * asteroid.getScale());
		shapey[4] = (float) (y + Math.sin(radians + 4 * Math.PI / 6) * asteroid.getScale());
		
		shapex[5] = (float) (x + Math.cos(radians + 2 * Math.PI / 6) * asteroid.getScale());
		shapey[5] = (float) (y + Math.sin(radians + 2 * Math.PI / 6) * asteroid.getScale());
		
		asteroid.setShapeX(shapex);
		asteroid.setShapeY(shapey);
	}
	
	private void updateEntity(Entity entity, MovingPart movingPart){
		
	}
}
