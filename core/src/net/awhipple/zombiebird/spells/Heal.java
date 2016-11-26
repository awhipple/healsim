package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Rejuvinate;

public class Heal extends Spell {

  private Healable target;

  public Heal(Healable target) {
    super(1.0f);
    this.target = target;
  }

  @Override
  public void resolveSpell() {
    target.heal(40.0f);
  }

  public static Spell getSpell(Raid raid, Healable target) {
    return new Heal(target);
  }

  public static class Factory implements SpellFactory {
    @Override
    public Spell getSpell(Raid raid) {
      return new Heal(raid.getHealer().getTarget());
    }
  }
}