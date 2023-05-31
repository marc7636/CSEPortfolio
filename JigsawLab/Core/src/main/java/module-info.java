import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPreEntityProcessingService;

module Core {
	requires Common;
	requires java.desktop;
	requires com.badlogic.gdx;
	requires spring.context;
	
	uses IGamePluginService;
	uses IEntityProcessingService;
	uses IPostEntityProcessingService;
	uses IPreEntityProcessingService;
	
	exports dk.sdu.mmmi.cbse.main;
}