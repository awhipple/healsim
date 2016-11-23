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
  private Healer healer = new Healer();

  public GameWorld() {
    this.raid = new Raid(20);
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());

    this.healer = new Healer();
    this.healer.target(raid.getHeroes()[0]);
  }

  public void update(float delta) {
    Gdx.app.log("GameWorld", "update");

    Hero[] heroes = raid.getHeroes();

    if(Math.random() > 0.99) heroes[(int)(Math.random()*heroes.length)].dealDamage(50.0f);
    healer.update();

    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
      int clickX = Gdx.input.getX();
      int clickY = Gdx.input.getY();

      for(int i = 0; i < heroes.length; i++) {
        Hero hero = heroes[i];
        if( clickX >= hero.getXPos() && clickX <= hero.getXPos() + GameRenderer.HERO_BAR_WIDTH &&
            clickY >= hero.getYPos() && clickY <= hero.getYPos() + GameRenderer.HERO_BAR_HEIGHT) {
              healer.target(hero);
        }
      }
    }

    if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
      healer.startCast();
    }
  }

  public Raid getRaid() { return raid; }
  public Healer getHealer() { return healer; }
}
