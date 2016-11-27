package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameWorld {

  private Raid raid = new Raid(20);
  private float tankHit;

  public GameWorld() {
    this.raid = new Raid(20);
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());

    tankHit = 2.0f;
  }

  public void update(float delta) {
    Gdx.app.log("Gameworld", "updating");

    Hero[] heroes = raid.getHeroes();
    for(int i = 0; i < heroes.length; i++) {
      heroes[i].updateMods(delta);
    }

    if (Math.random() > 0.98) heroes[(int) (Math.random() * heroes.length)].dealDamage(30.0f);
    tankHit -= delta;
    if(tankHit <= 0) {
      if(heroes[0].isDead() && tankHit <= -2.0f) {
        heroes[(int)(Math.random()*heroes.length)].dealDamage(100.0f);
        tankHit += 4.0f;
      } else if(!heroes[0].isDead()) {
        tankHit += 2.0f;
        heroes[0].dealDamage(15.0f);
      }
    }

    if(raid.getHealer().getHero().isDead()) {
      raid.getHealer().stopCast();
    } else {
      raid.getHealer().update(delta);
    }
  }

  public Raid getRaid() { return raid; }
}
