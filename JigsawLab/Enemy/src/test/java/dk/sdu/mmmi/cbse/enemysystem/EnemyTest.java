package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;

public class EnemyTest {
	
	@Mock
	private GameData gameData;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		when(gameData.getDisplayWidth()).thenReturn((int) 500f);
		when(gameData.getDisplayHeight()).thenReturn((int) 400f);
	}
	
	@Test
	void testCreateEnemyShip() {
		Entity enemyShip = EnemyPlugin.createEnemy(gameData);
		
		// Verify that the player ship has the correct components
		Assertions.assertNotNull(enemyShip.getPart(MovingPart.class));
		Assertions.assertNotNull(enemyShip.getPart(PositionPart.class));
		Assertions.assertNotNull(enemyShip.getPart(LifePart.class));
		
		// Verify that the components have the correct values
		MovingPart movingPart = enemyShip.getPart(MovingPart.class);
		Assertions.assertEquals(10f, movingPart.getDeceleration());
		Assertions.assertEquals(200f, movingPart.getAcceleration());
		Assertions.assertEquals(300f, movingPart.getMaxSpeed());
		Assertions.assertEquals(5f, movingPart.getRotationSpeed());
		
		PositionPart positionPart = enemyShip.getPart(PositionPart.class);
		Assertions.assertEquals(gameData.getDisplayWidth()/4, positionPart.getX());
		Assertions.assertEquals(gameData.getDisplayHeight()/4, positionPart.getY());
		Assertions.assertEquals(3.1415f / 2, positionPart.getRadians());
		
		LifePart lifePart = enemyShip.getPart(LifePart.class);
		Assertions.assertEquals(2, lifePart.getLife());
	}
}