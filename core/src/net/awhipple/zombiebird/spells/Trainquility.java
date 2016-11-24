package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Trainquility extends Spell {

  private static float HEAL_PER_SECOND = 5.0f;

  Hero[] heroes;

  public Trainquility(Hero[] heroes) {
    super(5.0f);
    this.heroes = heroes;
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    for(int i = 0; i < heroes.length; i++) {
      heroes[i].heal(delta * HEAL_PER_SECOND);
    }
  }

}
