package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameworld.GameRenderer;
import net.awhipple.zombiebird.gameworld.GameWorld;
import net.awhipple.zombiebird.spells.Heal;
import net.awhipple.zombiebird.spells.Rejuvination;
import net.awhipple.zombiebird.spells.Spell;
import net.awhipple.zombiebird.spells.SpellFactory;
import net.awhipple.zombiebird.spells.Trainquility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputHandler {
  private GameWorld world;
  private SpellFactory spellFactories[];

  public InputHandler(GameWorld world) {
    this.world = world;
    spellFactories = new SpellFactory[] { null, new Heal.Factory(), new Rejuvination.Factory(), new Trainquility.Factory() };
  };

  public void processInput() {
    Hero[] heroes = world.getRaid().getHeroes();
    Healer healer = world.getRaid().getHealer();

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

    Iterator<Pair<Integer, SpellFactory>> iterator = healer.getSkillSet().iterator();
    while(iterator.hasNext()) {
      Pair<Integer, SpellFactory> keyBind = iterator.next();
      if(Gdx.input.isKeyPressed(keyBind.getLeft())) {
        healer.startCast(keyBind.getRight().getSpell(world.getRaid()));
        break;
      }
    }

    if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      healer.stopCast();
    }
  }
}

