package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameWorld {

  private Raid raid = new Raid(20);
  private Healer healer = new Healer();

  public GameWorld() {
    this.raid = new Raid(20);
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());

    this.healer = new Healer();
    this.healer.target(raid.getHeroes()[0]);
  }

  public void update(float delta) {
    Gdx.app.log("GameWorld", "update");

    raid.getHeroes()[0].dealDamage(0.5f);
    healer.update();
  }

  public Raid getRaid() { return raid; }
  public Healer getHealer() { return healer; }
}
