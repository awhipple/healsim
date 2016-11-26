package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.mod.Rejuvinate;

public class Rejuvination extends Spell {

  private Healable target;

  public Rejuvination(Healable target) {
    super(0f);
    this.target = target;
  }

  @Override
  public void resolveSpell() {
    target.addMod(new Rejuvinate(target));
  }

  public static class Factory implements SpellFactory {
    @Override
    public Spell getSpell(Raid raid, Healable target) {
      return new Rejuvination(target);
    }
  }
}