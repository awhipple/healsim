package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.bosses.Boss;
import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameWorld {

  private Raid raid;
  private Boss boss;

  public GameWorld() {
    this.raid = new Raid(20);
    this.boss = new Boss(100.0f, this.raid);
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());
  }

  public void update(float delta) {
    Gdx.app.log("Gameworld", "updating");

    Hero[] heroes = raid.getHeroes();
    for(int i = 0; i < heroes.length; i++) {
      heroes[i].updateMods(delta);
    }

    boss.update(delta);

    if(raid.getHealer().getHero().isDead()) {
      raid.getHealer().stopCast();
    } else {
      raid.getHealer().update(delta);
    }
  }

  public Raid getRaid() { return raid; }
  public Boss getBoss() { return boss; }
}
