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
  private Healer healer;

  public GameWorld() {
    this.raid = new Raid(20);
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());

    this.healer = new Healer(raid);
  }

  public void update(float delta) {
    Gdx.app.log("GameWorld", "update");

    Hero[] heroes = raid.getHeroes();

    if (Math.random() > 0.98) heroes[(int) (Math.random() * heroes.length)].dealDamage(30.0f);
    healer.update(delta);
  }

  public Raid getRaid() { return raid; }
  public Healer getHealer() { return healer; }
}
