package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Trainquility extends Spell {

  private static float HEAL_PER_SECOND = 5.0f;

  Healable[] heroes;
  private float nextHeal;

  public Trainquility(Healable[] heroes) {
    super(5.0f);
    this.heroes = heroes;
    this.nextHeal = 4.0f;
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    if(castTimeRemaining <= nextHeal) {
      for(int i = 0; i < heroes.length; i++) {
        heroes[i].heal(HEAL_PER_SECOND);
      }
      nextHeal -= 1.0f;
    }
  }

  public static class Factory implements SpellFactory {
    @Override
    public Spell getSpell(Raid raid, Healable target) {
      return new Trainquility(raid.getHeroes());
    }
  }
}
