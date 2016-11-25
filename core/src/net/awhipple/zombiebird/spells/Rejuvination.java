package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
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
}