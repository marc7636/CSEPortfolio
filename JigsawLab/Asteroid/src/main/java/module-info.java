import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidController;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
	requires Common;
	provides IEntityProcessingService with AsteroidController;
	provides IGamePluginService with AsteroidPlugin;
}