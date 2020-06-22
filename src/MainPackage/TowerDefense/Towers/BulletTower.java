package MainPackage.TowerDefense.Towers;

import MainPackage.TowerDefense.AmmoTypeEnum;
import OOFramework.Maths.Vector2;

public class BulletTower extends TowerBase {
    public BulletTower(Vector2 pos) {
        super(pos, 64, 18, 6, 250);
        this.towerBuilding.SetImageByFileName("towerDefense_tile181.png");
        this.towerGun.SetImageByFileName("towerDefense_tile249.png");
        this.damage = 17;
        this.attackCooldown = 1;
        this.upgradeLevel = 1;
        this.ammo = AmmoTypeEnum.BULLETS;
    }
}
