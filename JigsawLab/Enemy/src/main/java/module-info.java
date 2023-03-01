import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyAI;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

module Enemy {
	requires Common;
	provides IEntityProcessingService with EnemyAI;
	provides IGamePluginService with EnemyPlugin;
}