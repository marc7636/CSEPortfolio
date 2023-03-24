import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
	requires Common;
	requires CommonBullet;
	provides BulletSPI with BulletControlSystem;
	provides IGamePluginService with BulletPlugin;
	provides IEntityProcessingService with BulletControlSystem;
}