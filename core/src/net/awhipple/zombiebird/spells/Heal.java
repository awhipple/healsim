package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
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
}
