package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameworld.GameRenderer;
import net.awhipple.zombiebird.gameworld.GameWorld;
import net.awhipple.zombiebird.spells.Heal;
import net.awhipple.zombiebird.spells.Rejuvination;
import net.awhipple.zombiebird.spells.Trainquility;

public class InputHandler {
  private GameWorld world;

  public InputHandler(GameWorld world) {
    this.world = world;
  }

  public void processInput() {
    Hero[] heroes = world.getRaid().getHeroes();
    Healer healer = world.getHealer();

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
      healer.startCast(new Heal(healer.getTarget()));
    }

    if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
      healer.startCast(new Rejuvination(healer.getTarget()));
    }

    if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
      healer.startCast(new Trainquility(world.getRaid().getHeroes()));
    }

    if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      healer.stopCast();
    }
  }
}

