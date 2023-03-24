package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
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
			world.addEntity(createAsteroid(gameData));
		}
		
		for (Asteroid asteroid : world.getEntities(Asteroid.class)) {
			PositionPart positionPart = asteroid.getPart(PositionPart.class);
			MovingPart movingPart = asteroid.getPart(MovingPart.class);
			LifePart lifePart = asteroid.getPart(LifePart.class);
			
			movingPart.process(gameData, asteroid);
			positionPart.process(gameData, asteroid);
			
			if (lifePart != null){
				lifePart.process(gameData, asteroid);
				if(lifePart.isHit()){
					splitAsteroid(asteroid, gameData, world);
					lifePart.setIsHit(false);
				}
			}
			
			updateShape(asteroid);
		}
	}
	
	private Asteroid createAsteroid(GameData gameData) {
		float deceleration = 0;
		float acceleration = 300000f;
		float maxSpeed = 100;
		float rotationSpeed = 3;
		PositionPart coords = getRandomSpawn(gameData);
		
		OptionalInt randomSize = rng.ints(1, 3, 9).findAny();
		Asteroid asteroid = new Asteroid(randomSize.isPresent() ? randomSize.getAsInt() : 8);
		asteroid.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed));
		asteroid.add(coords);
		
		MovingPart movingPart = asteroid.getPart(MovingPart.class);
		movingPart.setUp(true);
		
		asteroid.setShapeX(new float[6]);
		asteroid.setShapeY(new float[6]);
		
		asteroid.add(new LifePart((asteroid.getSize() - 1) / 2 ));
		
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
	
	@SuppressWarnings("DuplicatedCode")
	private void splitAsteroid(Asteroid asteroid, GameData gameData, World world){
		MovingPart movingPart = asteroid.getPart(MovingPart.class);
		PositionPart coords = asteroid.getPart(PositionPart.class);
		LifePart lifePart = asteroid.getPart(LifePart.class);
		
		Asteroid asteroid1 = new Asteroid(asteroid.getSize() / 2);
		asteroid1.add(movingPart.clone());
		asteroid1.add(coords.clone());
		asteroid1.setShapeX(asteroid.getShapeX());
		asteroid1.setShapeY(asteroid.getShapeY());
		asteroid1.add(new LifePart(lifePart.getLife() - 1));
		
		Asteroid asteroid2 = new Asteroid(asteroid.getSize() / 2);
		asteroid2.add(movingPart.clone());
		asteroid2.add(coords.clone());
		asteroid2.setShapeX(asteroid.getShapeX());
		asteroid2.setShapeY(asteroid.getShapeY());
		asteroid2.add(new LifePart(lifePart.getLife() - 1));
		
		PositionPart pos1 = asteroid1.getPart(PositionPart.class);
		pos1.setRadians(coords.getRadians() - (float) Math.PI / 4);
		
		PositionPart pos2 = asteroid2.getPart(PositionPart.class);
		pos2.setRadians(coords.getRadians() + (float) Math.PI / 2);
		
		world.removeEntity(asteroid);
		world.addEntity(asteroid1);
		world.addEntity(asteroid2);
	}
}
