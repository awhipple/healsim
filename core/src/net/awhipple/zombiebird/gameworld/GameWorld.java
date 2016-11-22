package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameWorld {

  private Raid raid = new Raid(20);

  public void update(float delta) {
    Gdx.app.log("GameWorld", "update");

    raid.getHeroes()[0].dealDamage(0.5f);
  }

  public Raid getRaid() { return raid; }
}
